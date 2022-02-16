package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Student;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.model.StudentModel;

public interface StudentService extends IService<Student> {


    StudentModel getById(Long sId);

    PageResult getStudents(PageParam model);

    void remove(Long []ids);

    void add(StudentModel studentModel);

    PageResult online();

    void offline(String ids[]);

}
