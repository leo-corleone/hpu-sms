package com.tams.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.Clazz;
import com.tams.domain.Department;
import com.tams.domain.Teacher;
import com.tams.dto.PageResult;
import com.tams.mapper.TeacherMapper;
import com.tams.model.TeacherModel;
import com.tams.service.ClassService;
import com.tams.service.DepartmentService;
import com.tams.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper , Teacher> implements TeacherService {

    @Resource
    private ClassService classService;

    @Resource
    private DepartmentService departmentService;

    @Override
    public PageResult getAll() {

        List<Teacher> teachers = super.list();
        List<TeacherModel> teacherModels = new ArrayList<>(teachers.size());

        teachers.forEach(teacher -> {
            TeacherModel teacherModel = new TeacherModel();
            BeanUtil.copyProperties(teacher ,teacherModel);
            List<String> classNames = classService.lambdaQuery().select(Clazz::getClassName)
                    .eq(Clazz::getTId, teacher.getTId())
                    .list().stream().map(Clazz::getClassName).collect(Collectors.toList());
            Department one = departmentService.lambdaQuery().select(Department::getDepartName).eq(Department::getDId, teacherModel.getDId()).one();
            teacherModel.setClazz(classNames);
            teacherModel.setIsT(!classNames.isEmpty());
            teacherModel.setDepartment(one.getDepartName());
            teacherModels.add(teacherModel);
        });
        PageResult pageResult = new PageResult();
        pageResult.setTotal(Long.valueOf(teacherModels.size()));
        pageResult.setItems(teacherModels);
        return pageResult;
    }
}
