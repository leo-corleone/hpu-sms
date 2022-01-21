package com.tams.exception.login;

import lombok.Data;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Data
public class LoginException extends RuntimeException{

    private String msg;

    private Integer code;

    public LoginException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public LoginException(String msg) {
        this.msg = msg;
    }

}