package com.tams.model;

import com.tams.enums.RightTypeEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * @author swiChen
 * @date 2022/3/18
 **/

@Data
public class UserRightModel implements Serializable {

 private Long uId;

 @Enumerated(EnumType.STRING)
 private RightTypeEnum rightType;

 private Integer rtId;

 private Boolean isR;

 private Boolean isW;

}
