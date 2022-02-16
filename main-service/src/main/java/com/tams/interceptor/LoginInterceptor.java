package com.tams.interceptor;

import com.tams.base.jwt.JWTService;
import com.tams.base.jwt.util.JWTConstant;
import com.tams.base.redis.RedisService;
import com.tams.base.redis.util.RedisConstant;
import com.tams.enums.RoleEnum;
import com.tams.exception.base.BusinessException;
import com.tams.exception.jwt.JWTException;
import com.tams.model.LoginModel;
import com.tams.model.SysUser;
import com.tams.util.SysUserContextHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

public class LoginInterceptor implements HandlerInterceptor {

    private RedisService redisService;

    private JWTService jwtService;

    public LoginInterceptor(RedisService redisService, JWTService jwtService) {
        this.redisService = redisService;
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        if(request.getMethod().equalsIgnoreCase("options")){
            return true;
        }
        try {
          String token =  request.getHeader(JWTConstant.TOKEN_NAME);
          //1.token校验
          token = token.replace("Bearer" ,"").trim();
//        jwtService.verify(token);
          //2.获取token数
          LoginModel login = jwtService.verifyGetLoginBody(token);
          //3.判断token是否过期
          if (login.getRole() == RoleEnum.STUDENT) {
              CheckExpireToken(RedisConstant.TOKEN_STUDENT_PREFIX + login.getUsername(), token);
          } else {
              switch (login.getRole()) {
                  case TEACHER:
                      CheckExpireToken(RedisConstant.TOKEN_TEACHER_PREFIX + login.getUsername(), token);
                      break;
                  case ROOT:
                      CheckExpireToken(RedisConstant.TOKEN_ROOT_PREFIX + login.getUsername(), token);
                      break;
                  case ADMIN:
                      CheckExpireToken(RedisConstant.TOKEN_ADMIN_PREFIX + login.getUsername(), token);
                      break;
              }
          }
          SysUserContextHandler.setSysUser(new SysUser(new Long(login.getUsername()), login.getRole()));
      }catch (BusinessException  e){
          throw e;
      }
       return true;
    }

    private void CheckExpireToken(String redisK , String token){
        String tokenInRedis = redisService.get(redisK);
        if (tokenInRedis == null || "".equals(tokenInRedis)){
            throw new BusinessException("用户已被强制退出" , 501);
        }
        if (!token.equals(tokenInRedis)){
            throw new JWTException("用户已被其他地方登陆 请重新登陆" , 501);
        }
    }

}
