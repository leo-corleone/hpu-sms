package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("department")
public class Department implements Serializable{

   @TableId
   private Long dId;

   private String departName;

   private Long president;

   private Long vicePresident;

   private String office;
}
