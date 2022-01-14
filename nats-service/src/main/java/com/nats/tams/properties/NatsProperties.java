package com.nats.tams.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author swiChen
 * @date 2022/1/14
 **/


@Configuration
@ConfigurationProperties(prefix = NatsProperties.NATS_SERVICE)
public class NatsProperties {

    public final static String NATS_SERVICE = "spring.nats";

    private Integer port;

    private String  host;

    private String url;

    private Integer reconnectTime;


}
