package com.tams.enums;

import lombok.AllArgsConstructor;

/**
 * @author swiChen
 * @date 2022/1/13
 **/
@AllArgsConstructor
public enum OperationTypeEnum {

    R("读"),
    W("写");

    private String operation;

}
