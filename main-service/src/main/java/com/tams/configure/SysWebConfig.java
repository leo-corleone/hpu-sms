package com.tams.configure;

import com.tams.interceptor.LoginInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Component
public class SysWebConfig implements WebMvcConfigurer {



     @Override
     public void addInterceptors(InterceptorRegistry registry) {

          registry.addInterceptor(new LoginInterceptor())
                  .excludePathPatterns("/login")
                  .addPathPatterns("*/*");

     }
}
