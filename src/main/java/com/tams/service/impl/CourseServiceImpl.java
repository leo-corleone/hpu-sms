package com.tams.service.impl;


import com.tams.mapper.CourseMapper;
import com.tams.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseMapper courseMapper;
	


}
