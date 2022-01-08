package com.tams.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.Student;
import com.tams.mapper.StudentMapper;
import com.tams.service.StudentService;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {




}
