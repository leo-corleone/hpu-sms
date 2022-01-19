package com.tams.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tams.domain.Student;
import com.tams.model.StudentModel;

/**
 * @author swiChen
 * @date 2022/1/7
 **/
public interface StudentMapper extends BaseMapper<Student> {

    StudentModel getStudentById(Long sId);

}



