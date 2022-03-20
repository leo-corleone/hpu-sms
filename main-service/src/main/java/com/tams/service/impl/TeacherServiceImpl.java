package com.tams.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.Clazz;
import com.tams.domain.Department;
import com.tams.domain.Teacher;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.enums.ResponseCode;
import com.tams.exception.base.BusinessException;
import com.tams.mapper.TeacherMapper;
import com.tams.model.PasswordModel;
import com.tams.model.TeacherModel;
import com.tams.service.ClassService;
import com.tams.service.DepartmentService;
import com.tams.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper , Teacher> implements TeacherService {

    @Resource
    private ClassService classService;

    @Resource
    private DepartmentService departmentService;

    @Override
    public PageResult getList(PageParam pageParam) {

        List<Teacher> teachers = super.lambdaQuery().like(StringUtils.hasLength(pageParam.getKeyword()),Teacher::getName , pageParam.getKeyword()).list();
        List<TeacherModel> teacherModels = new ArrayList<>(teachers.size());

        teachers.forEach(teacher -> {
            TeacherModel teacherModel = new TeacherModel();
            BeanUtil.copyProperties(teacher ,teacherModel);
            List<String> classNames = classService.lambdaQuery().select(Clazz::getClassName)
                    .eq(Clazz::getTId, teacher.getTId())
                    .list().stream().map(Clazz::getClassName).collect(Collectors.toList());
            Department department = departmentService.lambdaQuery().eq(Department::getDId, teacherModel.getDId()).one();
            teacherModel.setClazz(classNames);
            teacherModel.setIsT(!classNames.isEmpty());
            teacherModel.setDepartment(department.getDepartName());
            if (ObjectUtil.isNotEmpty(department.getPresident()) && (teacher.getTId().longValue() == department.getPresident().longValue())){
                teacherModel.setIsPresident(true);
            }
            if (ObjectUtil.isNotEmpty(department.getVicePresident()) && (teacher.getTId().longValue() == department.getVicePresident().longValue())){
                teacherModel.setIsVicePresident(true);
            }
            teacherModels.add(teacherModel);
        });
        PageResult pageResult = new PageResult();
        pageResult.setTotal(Long.valueOf(teacherModels.size()));
        pageResult.setItems(teacherModels);
        return pageResult;
    }

    @Override
    public TeacherModel getOneId(Long uid) {

        Teacher teacher = getById(uid);
        Department department = departmentService.getById(teacher.getDId());
        TeacherModel teacherModel = new TeacherModel();
        BeanUtil.copyProperties(teacher , teacherModel);
        teacherModel.setDepartment(department.getDepartName());

        return teacherModel;
    }

    @Override
    @Transactional
    public Boolean deleteTeacher(Integer[] ids) {

        removeByIds(Arrays.asList(ids));

        LambdaUpdateWrapper<Clazz> clazzWrapper = new LambdaUpdateWrapper<>();
        clazzWrapper.set(Clazz::getTId , null).in(Clazz::getTId , Arrays.asList(ids));
        classService.update(clazzWrapper);

        LambdaUpdateWrapper<Department> departmentWrapper1 = new LambdaUpdateWrapper<>();
        departmentWrapper1.set(Department::getPresident , null).in(Department::getPresident , Arrays.asList(ids));
        departmentService.update(departmentWrapper1);

        LambdaUpdateWrapper<Department> departmentWrapper2 = new LambdaUpdateWrapper<>();
        departmentWrapper2.set(Department::getVicePresident , null).in(Department::getVicePresident , Arrays.asList(ids));
        departmentService.update(departmentWrapper2);

        return true;
    }

    @Override
    public Boolean updatePwd(PasswordModel passwordModel) {

        if (ObjectUtil.isEmpty(passwordModel.getNewPwd()) || ObjectUtil.isEmpty(passwordModel.getNewPwd())){
            throw new BusinessException("密码不能为空" , ResponseCode.NoContent.getCode());
        }

        if (ObjectUtil.isEmpty(passwordModel.getUId())){
            throw new BusinessException("用户Id不能为空" , ResponseCode.NoContent.getCode());
        }

        Teacher teacher = getById(passwordModel.getUId());
        if (!teacher.getPwd().equals(passwordModel.getCurrentPwd())){
            throw new BusinessException("旧密码输入错误" , 333);
        }

        if (teacher.getPwd().equals(passwordModel.getNewPwd())){
            throw new BusinessException("旧密码与新密码不能一直" , 333);
        }



        teacher.setPwd(passwordModel.getNewPwd());
        return updateById(teacher);
    }

}
