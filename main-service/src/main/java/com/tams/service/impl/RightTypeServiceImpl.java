package com.tams.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.RightType;
import com.tams.enums.RightTypeEnum;
import com.tams.mapper.RightTypeMapper;
import com.tams.service.RightTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author swiChen
 * @date 2022/3/11
 **/

@Service
public class RightTypeServiceImpl extends ServiceImpl<RightTypeMapper , RightType> implements RightTypeService {


    @Override
    public List<RightType> getList() {
        return this.lambdaQuery().ne(RightType::getType , RightTypeEnum.ALL).list();
    }
}
