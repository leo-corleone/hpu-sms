package com.tams.base.nats.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author swiChen
 * @date 2022/1/28
 **/

@Data
@Accessors(chain = true)
public class LoginNatsModel {


    private String msg;

    private LocalDateTime time;


}
