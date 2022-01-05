package org.sms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sms.domain.Student;
import org.sms.mapper.StudentMapper;
import org.sms.service.StudentService;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper , Student> implements StudentService{




}
