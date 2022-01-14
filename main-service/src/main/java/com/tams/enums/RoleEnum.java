package com.tams.enums;

import lombok.AllArgsConstructor;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@AllArgsConstructor
public enum RoleEnum {


    ROOT("超级管理员"),

    ADMIN("管理员");

    private String role;

    public String getRole() {
        return role;
    }
}
