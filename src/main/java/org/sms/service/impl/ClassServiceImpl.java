package org.sms.service.impl;


import org.sms.mapper.ClassMapper;
import org.sms.service.ClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClassServiceImpl implements ClassService {
	
	@Resource
	private ClassMapper classMapper;


}
