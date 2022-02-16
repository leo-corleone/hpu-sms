package com.tams.dto;

import lombok.Data;

/**
 * @author swiChen
 * @date 2022/2/9
 **/

@Data
public class PageResult<T> {

   private Long total;

   private T items;

}
