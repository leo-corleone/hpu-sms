package com.tams.exception.base;

import lombok.Data;

/**
 * @author swiChen
 * @date 2022/1/18
 **/
@Data
public class BusinessException extends RuntimeException{

    private String msg;

    private Integer code;

     public BusinessException(String msg, Integer code) {
            super(msg);
            this.msg = msg;
            this.code = code;
     }

     public BusinessException(String msg) {
           this.msg = msg;
     }
}
