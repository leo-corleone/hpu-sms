package com.tams.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@Data
@Accessors(chain = true)
@TableName("class")
public class Clazz implements Serializable {

    @TableId
    private Long cId;

    private String className;

    private Long tId;

    private Long dId;

    private Integer total;

    private Integer grade;

    @TableLogic
    private Integer isDelete;
}
