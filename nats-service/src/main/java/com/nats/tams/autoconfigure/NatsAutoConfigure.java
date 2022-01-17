package com.nats.tams.autoconfigure;

import com.alibaba.fastjson.JSONObject;
import com.nats.tams.annotation.NatsListener;
import com.nats.tams.exception.NatsException;
import com.nats.tams.properties.NatsProperties;
import com.nats.tams.core.NatsClient;
import io.nats.client.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * @author swiChen
 * @date 2022/1/14
 **/
@Slf4j
@ConditionalOnClass(NatsClient.class)
@Configuration
@EnableConfigurationProperties(NatsProperties.class)
public class NatsAutoConfigure {

    private final NatsProperties properties;

    private final ApplicationContext context;

    public NatsAutoConfigure(NatsProperties properties , ApplicationContext context ) {
        this.properties = properties;
        this.context = context;
    }

    @Bean(initMethod = "init" , destroyMethod = "destroy")
    public NatsClient natsClient(){

        if (StringUtils.hasText(properties.getHost()) && properties.getPort() != null){
            String url = "nats://%s:%s";
            url = String.format(url, properties.getHost() , properties.getPort());
            NatsClient natsTemplate = new NatsClient(url
                    , properties.getReconnectWait()
                    , properties.getConnectionTimeout()
                    , properties.getMaxReconnects()
                    , properties.getPingInterval());
            init (natsTemplate);
            return natsTemplate;
        }
        if (StringUtils.hasText(properties.getUrl())){
            NatsClient natsTemplate = new NatsClient(properties.getUrl()
                    , properties.getReconnectWait()
                    , properties.getConnectionTimeout()
                    , properties.getMaxReconnects()
                    , properties.getPingInterval());
            init (natsTemplate);
            return natsTemplate;
        }
        return null;
    }

    public void init(NatsClient natsClient) {

      new Thread(()->{
            if (properties.getGlobalLog()){
                log.info("nats start subscribe subject");
            }
            String[] beanNames = context.getBeanDefinitionNames();
            for (String beanName : beanNames) {
                Object object = context.getBean(beanName);
                init(natsClient ,  object);
            }
            if (properties.getGlobalLog()){
                log.info("nats end subscribe subject");
            }
        },"nats-subscribe").start();
    }


    private void init(NatsClient natsClient , Object bean){

        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : methods) {
            if (!method.isAnnotationPresent(NatsListener.class)){continue;}
            NatsListener annotation = method.getAnnotation(NatsListener.class);

            MessageHandler handler = (msg)->{
                try {
                    if (method.getParameterTypes().length != 0){
                        Class<?> parameterType = method.getParameterTypes()[0];
                        String value = new String(msg.getData(), StandardCharsets.UTF_8);
                        Object object = JSONObject.parseObject(value, parameterType);
                        method.invoke(bean , object);
                    }else {
                        method.invoke(bean , (Object) null);
                    }
                    if (annotation.log()){
                       log.info("nats 订阅 {} 执行成功 ----- " , method.getName() );
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new NatsException(e.getMessage());
                }
            };

            String subject = annotation.subject();
            String queue = annotation.queue();
            natsClient.subscribe(subject,queue, handler);
            if (properties.getGlobalLog()){
                log.info("[ method-name:{} , subject:{} , queue:{} ] finishing subscribe", method.getName() , subject , queue.equals("") ? null : queue);
            }
        }

    }

}
