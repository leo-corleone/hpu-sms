package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tams.enums.RightTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author swiChen
 * @date 2022/1/13
 **/


@Data
@Accessors(chain = true)
@TableName("right_type")
public class RightType {

 @TableId
 private Long rtId;

 private String name;

 @Enumerated(EnumType.STRING)
 private RightTypeEnum type;

 private String remark;

}
