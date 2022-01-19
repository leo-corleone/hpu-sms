package com.nats.tams.exception;

import lombok.Data;

/**
 * @author swiChen
 * @date 2022/1/15
 **/

@Data
public class NatsException extends RuntimeException{

    private String msg;

    private Integer code;

    public NatsException(String msg){
        super(msg);
    }

    public NatsException() {
        super("");
    }


}
