package com.tams.init;

import com.tams.service.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

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
//@Profile("prod")
public class ConfigureInitTask implements InitializingBean {

    @Resource
    private MonitorService monitorService;

    private void autoOffline(){
        log.info("开始检测不在线用户.....");
        monitorService.autoOffline();
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

        scheduler.scheduleAtFixedRate(this::autoOffline, 5,20 , TimeUnit.SECONDS);
    }
}
