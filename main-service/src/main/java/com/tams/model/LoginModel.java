package com.tams.model;

import com.tams.enums.RoleEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Data
public class LoginModel {

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;



}
