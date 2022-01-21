package com.tams.exception.jwt;

import lombok.Data;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Data
public class JWTException extends RuntimeException{

    private String msg;

    private Integer code;

    public JWTException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public JWTException(String msg) {
        this.msg = msg;
    }

}
