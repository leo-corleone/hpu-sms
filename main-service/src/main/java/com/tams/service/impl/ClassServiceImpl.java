package com.tams.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tams.domain.Clazz;
import com.tams.domain.Department;
import com.tams.domain.Teacher;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.mapper.ClassMapper;
import com.tams.model.ClassModel;
import com.tams.model.OptionsModel;
import com.tams.service.ClassService;
import com.tams.service.DepartmentService;
import com.tams.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper , Clazz> implements ClassService {

    @Resource
    private TeacherService teacherService;

    @Resource
    private DepartmentService departmentService;


    @Override
    public PageResult getList(PageParam pageParam) {

        Page<Object> objects = PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());

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

        PageResult pageResult = new PageResult();
        pageResult.setItems(classModels);
        pageResult.setTotal(objects.getTotal());

        return pageResult;
    }

    @Override
    public Set<OptionsModel> classDepartmentList() {

        List<Department> departments = departmentService.list();
        Set<OptionsModel> set = new HashSet<>(departments.size());

        departments.forEach(department -> {
            OptionsModel optionsModel = new OptionsModel();
            optionsModel.setLabel(department.getDepartName());
            optionsModel.setValue(String.valueOf(department.getDId()));
            List<Clazz> clazzes = this.lambdaQuery().eq(Clazz::getDId, department.getDId()).list();
            if (clazzes.size() > 0){
                List<OptionsModel> optionsModels = new ArrayList<>();
                clazzes.forEach(clazz -> {
                    OptionsModel optionsModel1 = new OptionsModel();
                    optionsModel1.setLabel(clazz.getClassName());
                    optionsModel1.setValue(String.valueOf(clazz.getCId()));
                    optionsModels.add(optionsModel1);
                });
                optionsModel.setChildren(optionsModels);
            }
            set.add(optionsModel);
        });

        return set;
    }
}
