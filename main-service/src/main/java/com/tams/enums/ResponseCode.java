package com.tams.enums;

import lombok.AllArgsConstructor;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@AllArgsConstructor

public enum ResponseCode {

    OK(200,"OK"),

    UNRight(207 , "用户权限不足"),

    UNAdmin(208 , "非管理员登陆"),

    UnAuth(401 , "身份校验失败"),

    NoUser(402 , "用户不存在"),

    NoContent(407 , "请求内容为空");

    public Integer code;

    public String reason;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
