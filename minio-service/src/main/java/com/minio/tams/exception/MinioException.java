package com.minio.tams.exception;

/**
 * @author swiChen
 * @date 2022/1/20
 **/
public class MinioException extends RuntimeException{

    private String msg;

    private Integer code;

    public MinioException(){}

    public MinioException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public MinioException(String msg) {
        this.msg = msg;
    }
}
