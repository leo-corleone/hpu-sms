package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Student;
import com.tams.dto.PageParam;
import com.tams.model.StudentModel;

import java.util.List;

public interface StudentService extends IService<Student> {


    StudentModel getById(Long sId);

    List<StudentModel> getStudents(PageParam model);

}
