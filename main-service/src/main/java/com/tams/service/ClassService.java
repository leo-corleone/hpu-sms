package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Clazz;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.model.ClassModel;
import com.tams.model.OptionsModel;

import java.util.List;
import java.util.Set;

public interface ClassService extends IService<Clazz> {

    PageResult getList(PageParam pageParam);

    Set<OptionsModel> classDepartmentList();
	
}
