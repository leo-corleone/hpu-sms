package com.nats.tams.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author swiChen
 * @date 2022/1/14
 **/


@ConfigurationProperties(prefix = NatsProperties.NATS_SERVICE)
public class NatsProperties {

    public final static String NATS_SERVICE = "spring.nats";

    private Integer port;

    private String  host;

    private String url;

    private Long reconnectWait;

    private Integer connectionTimeout;

    private Integer maxReconnects;

    private Integer pingInterval;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getReconnectWait() {
        return reconnectWait;
    }

    public void setReconnectWait(Long reconnectWait) {
        this.reconnectWait = reconnectWait;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getMaxReconnects() {
        return maxReconnects;
    }

    public void setMaxReconnects(Integer maxReconnects) {
        this.maxReconnects = maxReconnects;
    }

    public Integer getPingInterval() {
        return pingInterval;
    }

    public void setPingInterval(Integer pingInterval) {
        this.pingInterval = pingInterval;
    }
}
