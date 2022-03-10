package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tams.enums.RoleEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

/**
 * @author swiChen
 * @date 2022/3/7
 **/
@Accessors(chain = true)
@Data
@TableName("online_user")
public class OnlineUser implements Serializable {

    @TableId
    private Integer id;

    private Long uId;

    private String name;

    private String browser;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private String operationSystem;

    private String device;

    private String ip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date loginTime;

}
