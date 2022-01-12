package com.tams.service.impl;

import com.tams.mapper.TeacherMapper;
import com.tams.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class TeacherServiceImpl implements TeacherService {

	@Resource
	private TeacherMapper teacherMapper;

}
