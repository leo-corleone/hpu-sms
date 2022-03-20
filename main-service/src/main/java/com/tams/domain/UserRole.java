package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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


    private Long rId;

    private Long uId;

    private Date createTime;

    private Long createBy;

    @Enumerated(EnumType.STRING)
    private String status;


}
