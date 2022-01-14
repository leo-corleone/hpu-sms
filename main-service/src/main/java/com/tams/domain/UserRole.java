package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@Data
@TableName("user_role")
@Accessors(chain = true)
public class UserRole implements Serializable {

    @TableId
    private Long rId;

    private Long uId;

    private Date createTime;

    private Long createBy;

    private String state;

    @TableLogic
    private String isDelete;

}
