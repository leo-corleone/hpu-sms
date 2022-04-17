package com.tams.model;

import lombok.Data;

/**
 * @author swiChen
 * @date 2022/4/16
 **/
@Data
public class ResultModel<T> {

   private Integer code;

   private String msg;

   private T data;

}
