package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Clazz;

import java.util.List;

public interface ClassService extends IService<Clazz> {

    List<Clazz> getIdAndClassName();

	
}
