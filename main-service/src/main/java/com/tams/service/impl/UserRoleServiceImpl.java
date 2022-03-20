package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.annotation.Permission;
import com.tams.domain.RightType;
import com.tams.domain.Role;
import com.tams.domain.UserRole;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.ResponseCode;
import com.tams.enums.RightTypeEnum;
import com.tams.exception.base.BusinessException;
import com.tams.mapper.UserRoleMapper;
import com.tams.model.SysUser;
import com.tams.model.UserRightModel;
import com.tams.service.RightTypeService;
import com.tams.service.RoleService;
import com.tams.service.UserRoleService;
import com.tams.util.SysUserContextHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper , UserRole> implements UserRoleService {

    @Resource
    private RightTypeService rightTypeService;
    
    @Resource
    private RoleService roleService;

    @Override
    public boolean verifyRight(Permission permission) {

        if (ObjectUtil.isEmpty(permission)){
            throw new BusinessException("权限信息不能为空" , ResponseCode.NoContent.code);
        }
        SysUser sysUser = SysUserContextHandler.getSysUser();
        OperationTypeEnum operation = permission.operation();
        RightTypeEnum right = permission.right();

        // 判断当前请求的用户  是否有权限
        boolean isRight = false;
        List<UserRole> userRoles = this.lambdaQuery().in(UserRole::getUId, sysUser.getUId()).list();
        List<Role> roles  = new ArrayList<>();
        userRoles.forEach(userRole -> {
            Role role = roleService.lambdaQuery().eq(Role::getRId, userRole.getRId()).one();
            roles.add(role);
        });
        Set<Integer> rts = roles.stream().map(Role::getRtId).collect(Collectors.toSet());
        for (Integer rt : rts) {
                RightType rightType = rightTypeService.getById(rt);
                if (rightType.getType() == right || rightType.getType() == RightTypeEnum.ALL){
                    for (Role role : roles) {
                        if (role.getRtId().intValue() == rt.intValue() && role.getOperation()  == operation ){
                            isRight = true;
                            break;
                        }
                    }
                    break;
                }
        }
        return isRight;
    }

    @Override
    public UserRightModel getRight(UserRightModel userRightModel) {

        if (ObjectUtil.isEmpty(userRightModel.getRtId())){
            throw new BusinessException("权限Id不能为空" , ResponseCode.NoContent.getCode());
        }
        if (ObjectUtil.isEmpty(userRightModel.getUId())){
            throw new BusinessException("用户Id不能为空" , ResponseCode.NoContent.getCode());
        }

        List<Role> roles = roleService.lambdaQuery().eq(Role::getRtId, userRightModel.getRtId()).list();
        roles.forEach(role -> {
           if (role.getOperation() == OperationTypeEnum.W){
               UserRole userRole = this.lambdaQuery().eq(UserRole::getUId, userRightModel.getUId()).eq(UserRole::getRId, role.getRId()).one();
               userRightModel.setIsW(ObjectUtil.isNotEmpty(userRole));
           }
           if (role.getOperation() == OperationTypeEnum.R){
               UserRole userRole = this.lambdaQuery().eq(UserRole::getUId, userRightModel.getUId()).eq(UserRole::getRId, role.getRId()).one();
               userRightModel.setIsR(ObjectUtil.isNotEmpty(userRole));
           }
        });

        return userRightModel;
    }

    @Override
    @Transactional
    public boolean updateRight(UserRightModel userRightModel) {

        if (ObjectUtil.isEmpty(userRightModel.getRightType())){
            throw new BusinessException("权限类型不能为空", ResponseCode.NoContent.getCode());
        }

        if (ObjectUtil.isEmpty(userRightModel.getUId())){
            throw new BusinessException("用户Id不能为空" , ResponseCode.NoContent.getCode());
        }


        RightType rightType = rightTypeService.lambdaQuery().eq(RightType::getType, userRightModel.getRightType()).one();
        List<Role> roleList = roleService.lambdaQuery().eq(Role::getRtId, rightType.getRtId()).list();
        roleList.forEach(role -> {
            this.lambdaUpdate().eq(UserRole::getRId , role.getRId() ).eq(UserRole::getUId , userRightModel.getUId()).remove();
        });

        rightType = rightTypeService.lambdaQuery().eq(RightType::getType, userRightModel.getRightType()).one();
        SysUser sysUser = SysUserContextHandler.getSysUser();
        if (userRightModel.getIsR()){
            Role role = roleService.lambdaQuery().eq(Role::getRtId, rightType.getRtId()).eq(Role::getOperation, OperationTypeEnum.R).one();
            UserRole userRole = new UserRole();
            userRole.setRId(role.getRId())
                    .setUId(userRightModel.getUId())
                    .setCreateBy(sysUser.getUId())
                    .setCreateTime(new Date());
            save(userRole);
        }
        if (userRightModel.getIsW()){
            Role role = roleService.lambdaQuery().eq(Role::getRtId, rightType.getRtId()).eq(Role::getOperation, OperationTypeEnum.W).one();
            UserRole userRole = new UserRole();
            userRole.setRId(role.getRId())
                    .setUId(userRightModel.getUId())
                    .setCreateBy(sysUser.getUId())
                    .setCreateTime(new Date());
            save(userRole);
        }

        return true;
    }


}
