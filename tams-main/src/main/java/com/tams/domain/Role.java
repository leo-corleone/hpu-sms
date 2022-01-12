package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tams.enums.RoleEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@TableName("role")
@Data
@Accessors(chain = true)
public class Role implements Serializable {

    @TableId
    private Long rId;

    private RoleEnum role;

    private Integer dateScope;

    private String remark;



}
