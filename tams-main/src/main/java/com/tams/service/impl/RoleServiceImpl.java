package com.tams.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.Role;
import com.tams.mapper.RoleMapper;
import com.tams.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper , Role> implements RoleService {

}
