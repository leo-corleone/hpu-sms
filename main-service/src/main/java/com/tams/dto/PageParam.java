package com.tams.dto;

import lombok.Data;

/**
 * @author swiChen
 * @date 2022/1/19
 **/


@Data
public class PageParam {

    private Integer page;

    private Integer pageSize;

    private String order;

    private String sortBy;

    private String keyword;

    private String type;
}
