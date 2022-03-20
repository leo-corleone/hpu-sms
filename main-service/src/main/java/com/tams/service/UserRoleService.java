package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.annotation.Permission;
import com.tams.domain.UserRole;
import com.tams.model.UserRightModel;

/**
 * @author swiChen
 * @date 2022/1/12
 **/
public interface UserRoleService extends IService<UserRole> {

    boolean verifyRight(Permission permission);

    UserRightModel getRight(UserRightModel userRightModel);

    boolean updateRight(UserRightModel userRightModel);

}
