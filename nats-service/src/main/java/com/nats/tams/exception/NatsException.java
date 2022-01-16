package com.nats.tams.exception;

/**
 * @author swiChen
 * @date 2022/1/15
 **/

public class NatsException extends RuntimeException{

    public NatsException(String msg){
        super(msg);
    }

    public NatsException() {
        super("");
    }


}
