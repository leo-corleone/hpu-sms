package com.tams.domain;

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
@TableName("course")
public class Course implements Serializable{

    @TableLogic
    private Long cNo;

    private String name;

    private Integer credit;

    private String type;


	
}
