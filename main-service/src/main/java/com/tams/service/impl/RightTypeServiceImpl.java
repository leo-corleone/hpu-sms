package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.annotation.Permission;
import com.tams.domain.RightType;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.exception.base.BusinessException;
import com.tams.mapper.RightTypeMapper;
import com.tams.model.SysUser;
import com.tams.service.RightTypeService;
import com.tams.util.SysUserContextHandler;
import org.springframework.stereotype.Service;

/**
 * @author swiChen
 * @date 2022/3/11
 **/

@Service
public class RightTypeServiceImpl extends ServiceImpl<RightTypeMapper , RightType> implements RightTypeService {




    @Override
    public boolean verifyRight(Permission permission) {

        if (ObjectUtil.isEmpty(permission)){
            throw new BusinessException("权限信息不能为空");
        }

        SysUser sysUser = SysUserContextHandler.getSysUser();
        OperationTypeEnum operation = permission.operation();
        RightTypeEnum right = permission.right();
        // 判断当前请求的用户  是否有权限
        // TODO 1.判断是否是ROOT用户 则直接返回 true



        return false;
    }
}
