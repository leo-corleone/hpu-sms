package com.nats.tams.exception;

/**
 * @author swiChen
 * @date 2022/1/15
 **/

public class NatsException extends RuntimeException{

    private String msg;

    public NatsException(String msg){
        super(msg);
    }

    public NatsException() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
