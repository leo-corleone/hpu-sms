package com.sms.service.impl;


import com.sms.mapper.ClassMapper;
import com.sms.service.ClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClassServiceImpl implements ClassService {
	
	@Resource
	private ClassMapper classMapper;


}
