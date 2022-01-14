package com.tams.enums;

import lombok.AllArgsConstructor;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@AllArgsConstructor
public enum ResponseCode {

    OK(200 , "ok"),

    ERROR(500 , "error");

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
