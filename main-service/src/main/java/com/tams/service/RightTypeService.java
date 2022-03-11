package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.annotation.Permission;
import com.tams.domain.RightType;

/**
 * @author swiChen
 * @date 2022/3/11
 **/
public interface RightTypeService extends IService<RightType> {

    boolean verifyRight(Permission permission);

}
