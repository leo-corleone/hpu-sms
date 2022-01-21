package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tams.base.jwt.JWTService;
import com.tams.base.redis.RedisService;
import com.tams.base.redis.util.Constant;
import com.tams.domain.Student;
import com.tams.domain.Teacher;
import com.tams.exception.base.BusinessException;
import com.tams.model.LoginModel;
import com.tams.service.StudentService;
import com.tams.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

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

   public String login(LoginModel login){

       if (ObjectUtil.isEmpty(login)){
          throw new BusinessException("登录信息为空" , 500);
       }
       String token;
       if ("student".equals(login.getRole())){
          Student student = studentService.lambdaQuery()
                           .eq(Student::getSId , new Long(login.getUsername()))
                           .eq(Student::getPwd , login.getPassword()).one();
          if (ObjectUtil.isEmpty(student)){
             throw new BusinessException("用户不存在", 501);
          }
          token = jwtService.createToken(login);
          redisService.set(Constant.TOKEN_STUDENT_PREFIX+student.getSId() , token , TimeUnit.MINUTES , Constant.TOKEN_RESTORE_TIME);
       }
       else {
         Teacher teacher = teacherService.lambdaQuery()
              .eq(Teacher::getTId , new Long(login.getUsername()))
              .eq(Teacher::getPwd , login.getPassword()).one();
          if (ObjectUtil.isEmpty(teacher)){
             throw new BusinessException("用户不存在", 501);
          }
          token = jwtService.createToken(login);
          redisService.set(Constant.TOKEN_TEACHER_PREFIX+teacher.getTId() , token , TimeUnit.MINUTES , Constant.TOKEN_RESTORE_TIME);
       }
      return token;
   }



}
