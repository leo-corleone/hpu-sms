package com.tams.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tams.base.jwt.JWTService;
import com.tams.base.jwt.util.JWTConstant;
import com.tams.base.redis.RedisService;
import com.tams.base.redis.util.RedisConstant;
import com.tams.exception.base.BusinessException;
import com.tams.exception.jwt.JWTException;
import com.tams.model.SysUser;
import com.tams.util.SysUserContextHandler;
import org.springframework.http.HttpStatus;
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
          String user = jwtService.verifyGetSysUser(token);
          SysUser sysUser = JSONObject.parseObject(user, SysUser.class);
          String redisK = RedisConstant.getRedisK(sysUser.getRole());
          redisK = redisK+sysUser.getUId();
          CheckExpireToken(redisK , token);
          SysUserContextHandler.setSysUser(sysUser);
      }catch (BusinessException  e){
          throw e;
      }
       return true;
    }

    private void CheckExpireToken(String redisK , String token){
        String tokenInRedis = redisService.getCacheHash(redisK , RedisConstant.USER_TOKEN_CACHE);
        if (tokenInRedis == null || "".equals(tokenInRedis)){
            throw new BusinessException("用户已被强制退出" , HttpStatus.UNAUTHORIZED.value());

        }
        if (!token.equals(tokenInRedis)){
            throw new JWTException("用户已被其他地方登陆 请重新登陆" , HttpStatus.UNAUTHORIZED.value());
        }
    }

}
