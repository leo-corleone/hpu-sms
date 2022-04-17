package com.tams.base.websocket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author swiChen
 * @date 2022/4/17
 **/
@Data
@EqualsAndHashCode
public class UserWsModel {

    private String role;

    private String id;

}
