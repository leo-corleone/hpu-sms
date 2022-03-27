package com.tams.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author swiChen
 * @date 2022/3/27
 **/

@Component
public class SpringUtil implements ApplicationContextAware {

     private static ApplicationContext applicationContext;

     @Override
     public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         if (SpringUtil.applicationContext == null){
           SpringUtil.applicationContext = applicationContext;
         }
     }

     public static ApplicationContext getApplicationContext(){
         return applicationContext;
     }

     public static <T> T getBean(Class<T> clazz){
         return getApplicationContext().getBean(clazz);
     }

}
