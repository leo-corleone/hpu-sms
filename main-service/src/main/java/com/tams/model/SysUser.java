package com.tams.model;

import com.tams.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author swiChen
 * @date 2022/1/21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    private Long uId;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
