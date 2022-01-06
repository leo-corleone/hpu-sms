package com.sms.service.impl;


import com.sms.mapper.CourseMapper;
import com.sms.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseMapper courseMapper;
	


}
