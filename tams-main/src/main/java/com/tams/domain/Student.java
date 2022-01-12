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
 * @date 2022/1/5
 **/

@Data
@Accessors(chain = true)
@TableName("student")
public class Student implements Serializable{

    @TableId
    private Long sId;

    private String name;

    private Integer age;

    private String gender;

    private String tel;

    private String image;

    private Date birthday;

    private Date timeOfEnrollment;

    private Long cId;

    private String pwd;

    private String status;

    @TableLogic
    private Integer isDelete;

}
