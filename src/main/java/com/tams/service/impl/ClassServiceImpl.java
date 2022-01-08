package com.tams.service.impl;


import com.tams.mapper.ClassMapper;
import com.tams.service.ClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClassServiceImpl implements ClassService {
	
	@Resource
	private ClassMapper classMapper;


}
