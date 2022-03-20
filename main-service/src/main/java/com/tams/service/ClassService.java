package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Clazz;
import com.tams.dto.PageParam;
import com.tams.model.ClassModel;

import java.util.List;

public interface ClassService extends IService<Clazz> {

    List<ClassModel> getList(PageParam pageParam);


	
}
