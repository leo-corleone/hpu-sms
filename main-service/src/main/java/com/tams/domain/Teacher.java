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
@TableName("teacher")
public class Teacher implements Serializable{

    @TableId
    private Long tId;

    private String name;

    private Integer age;

    private String gender;

    private Long iId;

    private Long dId;

    private String pwd;

    @TableLogic
    private int isDelete;

	
}
