package com.sms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sms.domain.Student;
import com.sms.mapper.StudentMapper;
import com.sms.service.StudentService;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {




}
