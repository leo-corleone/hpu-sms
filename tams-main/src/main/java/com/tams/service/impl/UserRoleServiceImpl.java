package com.tams.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.UserRole;
import com.tams.mapper.UserRoleMapper;
import com.tams.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper , UserRole> implements UserRoleService {
}
