package org.sms.service.impl;

import org.sms.mapper.TeacherMapper;
import org.sms.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class TeacherServiceImpl implements TeacherService{

	@Resource
	private TeacherMapper teacherMapper;

}
