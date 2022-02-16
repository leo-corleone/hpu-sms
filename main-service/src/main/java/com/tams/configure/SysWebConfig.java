package com.tams.configure;

import com.tams.base.jwt.JWTService;
import com.tams.base.redis.RedisService;
import com.tams.interceptor.LoginInterceptor;
import com.tams.interceptor.ProcessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Configuration
public class SysWebConfig implements WebMvcConfigurer {

     @Resource
     private RedisService redisService;

     @Resource
     private JWTService jwtService;

     @Override
     public void addInterceptors(InterceptorRegistry registry) {


          registry.addInterceptor(new ProcessInterceptor()).addPathPatterns("/**");
          registry.addInterceptor(new LoginInterceptor(redisService , jwtService))
                  .addPathPatterns("/**")
                  .excludePathPatterns("/login" ,"/error");

     }
}
