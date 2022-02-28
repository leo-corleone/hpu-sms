package com.tams.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Teacher;
import com.tams.dto.PageResult;

public interface TeacherService extends IService<Teacher> {

   PageResult getAll();

}
