package com.tams.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.tams.domain.Clazz;
import com.tams.domain.Department;
import com.tams.domain.Teacher;
import com.tams.dto.PageParam;
import com.tams.mapper.ClassMapper;
import com.tams.model.ClassModel;
import com.tams.service.ClassService;
import com.tams.service.DepartmentService;
import com.tams.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper , Clazz> implements ClassService {

    @Resource
    private TeacherService teacherService;

    @Resource
    private DepartmentService departmentService;


    @Override
    public List<ClassModel> getList(PageParam pageParam) {

        PageHelper.startPage(pageParam.getPage() , pageParam.getPageSize());
        List<Clazz> clazzs = this.lambdaQuery().like(ObjectUtil.isNotEmpty(pageParam.getKeyword()) ,Clazz::getClassName , pageParam.getKeyword()).list();

        List<ClassModel> classModels = new ArrayList<>(clazzs.size());
        clazzs.forEach(clazz -> {
            ClassModel classModel = new ClassModel();
            BeanUtils.copyProperties(clazz , classModel);
            Department department = departmentService.lambdaQuery().select(Department::getDepartName).eq(Department::getDId, clazz.getDId()).one();
            if (ObjectUtil.isNotEmpty(department)){
                classModel.setDepartment(department.getDepartName());
            }
            Teacher teacher = teacherService.lambdaQuery().select(Teacher::getName).eq(Teacher::getTId , clazz.getTId()).one();
            if(ObjectUtil.isNotEmpty(teacher)){
                classModel.setTeacher(teacher.getName());
            }
            classModels.add(classModel);
        });

        return classModels;
    }

}
