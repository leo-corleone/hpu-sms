package com.tams.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Teacher;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.model.TeacherModel;

public interface TeacherService extends IService<Teacher> {

   PageResult getList(PageParam page);

   Boolean deleteTeacher(Integer[] ids);

   Boolean updateTeacher(TeacherModel teacherModel);



}
