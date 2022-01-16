package com.nats.tams.service;

import com.alibaba.fastjson.JSONObject;
import com.nats.tams.exception.NatsException;
import io.nats.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * @author swiChen
 * @date 2022/1/15
 **/
@Slf4j
public class NatsTemplate implements DisposableBean {

    private final Connection nc;

    private Dispatcher dispatcher;

    private Boolean isClosed = false;

    public NatsTemplate(String url , Long reconnectWait  , Integer connectionTimeout ,
                        Integer maxReconnects , Integer pingInterval) {

        Options.Builder natsBuilder = new Options.Builder();

        if (StringUtils.hasText(url.trim())){
            natsBuilder.server(url);
        }
        if (reconnectWait != null){
            natsBuilder.reconnectWait(Duration.ofSeconds(reconnectWait));
        }
        if (connectionTimeout != null){
            natsBuilder.connectionTimeout(Duration.ofSeconds(connectionTimeout));
        }
        if (maxReconnects != null){
            natsBuilder.maxReconnects(maxReconnects);
        }
        if (pingInterval != null){
            natsBuilder.pingInterval(Duration.ofSeconds(pingInterval));
        }

        Options options = natsBuilder.build();
        try {
            this.nc = Nats.connect(options);
        }catch (Exception e) {
            log.error("nats connection error");
            throw  new NatsException(e.getMessage());
        }
        this.dispatcher = nc.createDispatcher();
    }


    public void subscribe(String subject , String queue , MessageHandler handler){

        if (queue == null || "".equals(queue)){
             subscribe( subject ,  handler) ;}
        else {
             if (subject == null || "".equals(subject)){
               throw  new NatsException("subject 不能为空");
             }
             if (handler == null){
               throw  new NatsException("handler 不能为空");
             }
        checkDispatcher();
        this.dispatcher.subscribe(subject, queue, handler);
        }
    }

    public void subscribe(String subject , MessageHandler handler){

        if (subject == null || "".equals(subject)){
            throw  new NatsException("subject 不能为空");
        }
        if (handler == null){
            throw  new NatsException("handler 不能为空");
        }
        checkDispatcher();
        this.dispatcher.subscribe(subject , handler);
    }

    public void publish(String subject , Object data){
        publish(subject ,JSONObject.toJSONBytes(data));
    }

    public void publish(String subject , byte[] data) {
        nc.publish(subject , data);
    }

    private void checkDispatcher(){
        if (ObjectUtils.isEmpty(this.dispatcher)){
            this.dispatcher = this.nc.createDispatcher();
        }
    }

    @Override
    public void destroy() {

        if (isClosed){return;}

        if (this.dispatcher != null){
            this.dispatcher = null;
        }
        if (this.nc != null){
            try {
                this.nc.close();
                this.isClosed = true;
                log.info("nats connection closed");
            } catch (InterruptedException e) {
                this.isClosed = false;
                log.error("nats connection close error");
                throw new NatsException(e.getMessage());
            }
        }
    }
}
