package com.tams.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.tams.base.jwt.JWTService;
import com.tams.base.jwt.util.JWTConstant;
import com.tams.base.redis.RedisService;
import com.tams.base.redis.util.RedisConstant;
import com.tams.enums.ResponseCode;
import com.tams.exception.base.BusinessException;
import com.tams.exception.jwt.JWTException;
import com.tams.model.SysUser;
import com.tams.util.SysUserContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author swiChen
 * @date 2022/1/21
 **/
@Slf4j
public class RequestAuthInterceptor implements HandlerInterceptor {

    private RedisService redisService;

    private JWTService jwtService;

    public RequestAuthInterceptor(RedisService redisService, JWTService jwtService) {
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
        String tokenInRedis = null;
        try {
             tokenInRedis = redisService.getCacheHash(redisK , RedisConstant.USER_TOKEN_CACHE);
        }catch (Exception e){
             throw new BusinessException(e.getCause().getMessage() , 501);
        }

        if (tokenInRedis == null || "".equals(tokenInRedis)){
            throw new BusinessException("用户已被强制退出" , ResponseCode.UnAuth.code);

        }
        if (!token.equals(tokenInRedis)){
            throw new JWTException("用户已被其他地方登陆 请重新登陆" , ResponseCode.UnAuth.code);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(request.getMethod().equalsIgnoreCase("options")){
            return ;
        }
        SysUser sysUser = SysUserContextHandler.getSysUser();
        String k = RedisConstant.getRedisK(sysUser.getRole()) + sysUser.getUId();
        String time = redisService.getValue(RedisConstant.USER_EXPIRE_TIME);
        if (ObjectUtil.isEmpty(time) || "".equals(time)){
            time = RedisConstant.USER_DEFAULT_RESTORE_TIME.toString();
        }
        redisService.expire(k , TimeUnit.MINUTES , Long.valueOf(time));

    }
}
