package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.nats.tams.core.NatsClient;
import com.tams.base.jwt.JWTService;
import com.tams.base.redis.RedisService;
import com.tams.base.redis.util.RedisConstant;
import com.tams.domain.Student;
import com.tams.domain.Teacher;
import com.tams.enums.RoleEnum;
import com.tams.exception.base.BusinessException;
import com.tams.model.LoginModel;
import com.tams.service.StudentService;
import com.tams.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
   private NatsClient natsClient;

   public String login(LoginModel login){

       if (ObjectUtil.isEmpty(login)){
          throw new BusinessException("登录信息为空" , 500);
       }
       String token;
       if (login.getRole() == RoleEnum.STUDENT){
          Student student = studentService.lambdaQuery()
                           .eq(Student::getSId , new Long(login.getUsername()))
                           .eq(Student::getPwd , login.getPassword()).one();
          if (ObjectUtil.isEmpty(student)){
             throw new BusinessException("用户不存在", 501);
          }
          token = jwtService.createToken(login);
          if (redisService.exists(RedisConstant.TOKEN_STUDENT_PREFIX+student.getSId())){
              log.info("用户: {} 被覆盖登陆" , student.getSId());
              //  发送nats通知  账号已在别处登陆
//              natsClient.publish("",""); //TODO
          }
          redisService.set(RedisConstant.TOKEN_STUDENT_PREFIX+student.getSId() , token , TimeUnit.MINUTES , RedisConstant.TOKEN_RESTORE_TIME);
       }
      else  {
         Teacher teacher = teacherService.lambdaQuery()
              .eq(Teacher::getTId , new Long(login.getUsername()))
              .eq(Teacher::getPwd , login.getPassword()).one();
          if (ObjectUtil.isEmpty(teacher)){
             throw new BusinessException("用户不存在", 501);
          }
           if (redisService.exists(RedisConstant.TOKEN_TEACHER_PREFIX+teacher.getTId())){
               log.info("用户: {} 被覆盖登陆" , teacher.getTId());
               //  发送nats通知  账号已在别处登陆
//               natsClient.publish("",""); //TODO
           }
          token = jwtService.createToken(login);
          redisService.set(RedisConstant.TOKEN_TEACHER_PREFIX+teacher.getTId() , token , TimeUnit.MINUTES , RedisConstant.TOKEN_RESTORE_TIME);
       }
      return token;
   }


}
