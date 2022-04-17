package com.tams.base.websocket.model;

import lombok.Data;

/**
 * @author swiChen
 * @date 2022/4/17
 **/
@Data
public class ResultWsModel <T> {

    private Integer code;

    private String msg;

    private T data;

}