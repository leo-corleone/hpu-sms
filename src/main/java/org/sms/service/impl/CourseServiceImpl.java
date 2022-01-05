package org.sms.service.impl;


import org.sms.mapper.CourseMapper;
import org.sms.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseMapper courseMapper;
	


}
