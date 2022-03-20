package com.tams.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Teacher;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.model.PasswordModel;
import com.tams.model.TeacherModel;

public interface TeacherService extends IService<Teacher> {

   PageResult getList(PageParam page);

   TeacherModel getOneId(Long uid);

   Boolean deleteTeacher(Integer[] ids);

   Boolean updatePwd(PasswordModel passwordModel);



}
