package com.sms.service.impl;

import com.sms.mapper.TeacherMapper;
import com.sms.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class TeacherServiceImpl implements TeacherService {

	@Resource
	private TeacherMapper teacherMapper;

}
