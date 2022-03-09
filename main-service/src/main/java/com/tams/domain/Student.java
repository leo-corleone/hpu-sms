package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@ToString
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

    private Long iId;

    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date timeOfEnrollment;

    @JsonFormat
    private Long cId;

    private String pwd;

    private String status;

    @TableLogic
    private Integer isDelete;

}
