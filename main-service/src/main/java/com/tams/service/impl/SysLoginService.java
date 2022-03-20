package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.tams.base.jwt.JWTService;
import com.tams.base.nats.NatsService;
import com.tams.base.redis.RedisService;
import com.tams.base.redis.util.RedisConstant;
import com.tams.domain.Student;
import com.tams.domain.Teacher;
import com.tams.domain.UserRole;
import com.tams.enums.ResponseCode;
import com.tams.enums.RoleEnum;
import com.tams.exception.base.BusinessException;
import com.tams.model.LoginModel;
import com.tams.model.SysUser;
import com.tams.service.StudentService;
import com.tams.service.TeacherService;
import com.tams.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Slf4j
@Service
public class SysLoginService {

   @Resource
   private JWTService jwtService;

   @Resource
   private RedisService redisService;

   @Resource
   private StudentService studentService;

   @Resource
   private TeacherService teacherService;

   @Resource
   private UserRoleService userRoleService;

   @Resource
   private NatsService natsService;

   private final Lock lock = new ReentrantLock();

   public String login(LoginModel login){

       if (ObjectUtil.isEmpty(login)){
          throw new BusinessException("登录信息为空" , ResponseCode.NoContent.code);
       }
       SysUser sysUser  = new SysUser();
       if (login.getRole() == RoleEnum.STUDENT){
          Student student = studentService.lambdaQuery()
                           .eq(Student::getSId , new Long(login.getUsername()))
                           .eq(Student::getPwd , login.getPassword()).one();
          if (ObjectUtil.isEmpty(student)){
             throw new BusinessException("用户不存在", ResponseCode.NoUser.code);
          }
          BeanUtils.copyProperties(student , sysUser);
          sysUser.setUId(student.getSId());
      }
      else if(login.getRole() == RoleEnum.TEACHER || login.getRole() == RoleEnum.ROOT || login.getRole() == RoleEnum.ADMIN){

           Teacher teacher = teacherService.lambdaQuery()
                   .eq(Teacher::getTId , new Long(login.getUsername()))
                   .eq(Teacher::getPwd , login.getPassword()).one();

          if (ObjectUtil.isEmpty(teacher)){
               throw new BusinessException("用户不存在", ResponseCode.NoUser.code);
          }

          if (login.getRole() == RoleEnum.ROOT || RoleEnum.ADMIN == login.getRole()){
              Integer count = userRoleService.lambdaQuery().eq(UserRole::getUId, login.getUsername()).count();
              if (count == 0){
                  throw new BusinessException("非管理员无法登录", ResponseCode.UNAdmin.code);
              }
          }
         BeanUtils.copyProperties(teacher , sysUser);
         sysUser.setUId(teacher.getTId());

      }else {
           throw new BusinessException("用户身份不存在", ResponseCode.NoUser.code);
      }

       sysUser.setRole(login.getRole());
       String user = JSONObject.toJSONString(sysUser);

      // 校验 用户是否已经登录
      verifyLogin(login);
      // 生成token
      String token = generateToken(user);
      cacheUser(login , user , token);
      return token;

   }

   private void verifyLogin(LoginModel login){

       String k = RedisConstant.getRedisK(login.getRole());
       k = k+login.getUsername();
       if (redisService.exists(k)){
           log.info("用户: {} role:{} 被覆盖登陆" , login.getUsername() , login.getRole().getRole());
           //  发送nats通知  账号已在别处登陆
//               natsClient.publish("","");
           String subject = login.getRole().getRole()+"."+login.getUsername();
           natsService.LoginNats(subject , null);
       }
   }

   private String generateToken(String user){
       return jwtService.createToken(user);
   }

   private void cacheUser(LoginModel login , String sysUser , String token){
       String k = RedisConstant.getRedisK(login.getRole()) + login.getUsername();
       redisService.cacheHash(k , RedisConstant.USER_INFO_CACHE , sysUser);
       redisService.cacheHash(k , RedisConstant.USER_TOKEN_CACHE , token);
       String time = redisService.getValue(RedisConstant.USER_EXPIRE_TIME);
       if (ObjectUtil.isEmpty(time) || "".equals(time)){
           time = RedisConstant.USER_DEFAULT_RESTORE_TIME.toString();
       }
       redisService.expire(k , TimeUnit.MINUTES , Long.valueOf(time));
   }

}
