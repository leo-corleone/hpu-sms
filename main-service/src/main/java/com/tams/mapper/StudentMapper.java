package com.tams.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tams.domain.Student;
import com.tams.dto.PageParam;
import com.tams.model.StudentModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author swiChen
 * @date 2022/1/7
 **/
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    StudentModel getStudentById(Long sId);

    List<StudentModel> getStudentAll(@Param("param") PageParam pageParam);

}



