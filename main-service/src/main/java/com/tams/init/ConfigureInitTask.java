package com.tams.init;

import com.tams.service.OnlineUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author swiChen
 * @date 2022/3/10
 **/

@Configuration
@Slf4j
@Profile("dev")
public class ConfigureInitTask implements InitializingBean {

    @Resource
    private OnlineUserService onlineUserService;

    private void autoOffline(){
        log.info("开始检测不在线用户.....");
        onlineUserService.autoOffline();
        log.info("结束检测不在线用户....");
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

        scheduler.scheduleAtFixedRate(this::autoOffline, 5,20 , TimeUnit.SECONDS);
    }
}
