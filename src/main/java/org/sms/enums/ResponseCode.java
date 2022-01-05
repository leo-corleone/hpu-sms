package org.sms.enums;

/**
 * @author swiChen
 * @date 2022/1/5
 **/
public enum ResponseCode {

    OK(200 , "ok"),

    ERROR(500 , "error");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
