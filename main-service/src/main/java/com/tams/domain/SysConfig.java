package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author swiChen
 * @date 2022/3/19
 **/

@Data
@TableName("sys_config")
public class SysConfig implements Serializable {

   @TableId
   private Long id;

   private String configName;


   private String configKey;


   private String configValue;

}
