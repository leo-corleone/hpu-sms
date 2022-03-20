package com.tams.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author swiChen
 * @date 2022/3/19
 **/

@Data
public class PasswordModel implements Serializable {

    private Long uId;

    private String currentPwd;

    private String newPwd;

}
