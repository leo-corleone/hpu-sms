package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.RightType;

import java.util.List;

/**
 * @author swiChen
 * @date 2022/3/11
 **/
public interface RightTypeService extends IService<RightType> {


     List<RightType> getList();


}
