package com.tams.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tams.base.redis.RedisService;
import com.tams.base.redis.util.RedisConstant;
import com.tams.domain.Clazz;
import com.tams.domain.Department;
import com.tams.domain.Student;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.exception.base.BusinessException;
import com.tams.mapper.StudentMapper;
import com.tams.model.StudentModel;
import com.tams.service.ClassService;
import com.tams.service.DepartmentService;
import com.tams.service.ImageService;
import com.tams.service.StudentService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
                            implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ImageService imageService;

    @Resource
    private RedisService redisService;

    @Resource
    private ClassService classService;

    @Resource
    private DepartmentService departmentService;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Override
    public StudentModel getById(Long sId) {

        StudentModel studentModel = studentMapper.getStudentById(sId);
        if (ObjectUtil.isEmpty(studentModel)){
            return null;
        }
        String imagePath = imageService.getImagePath(studentModel.getIId());
        studentModel.setImagePath(imagePath);
        return studentModel;
    }

    @Override
    public PageResult getStudents(PageParam model) {

        Page<Object> objects = PageHelper.startPage(model.getPage(), model.getPageSize());
        PageResult<List<StudentModel>> pageResult = new PageResult<>();
        pageResult.setItems(studentMapper.getStudentAll());
        pageResult.setTotal(objects.getTotal());
        return pageResult;
    }

    @Override
    public void remove(Long[] ids) {
        if (ObjectUtil.isEmpty(ids) || ids.length == 0){
            new BusinessException("id為不能空",501);
        }
        removeByIds(Arrays.asList(ids));
    }

    @Override
    public void add(StudentModel studentModel) {
        if (ObjectUtil.isEmpty(studentModel) || ObjectUtil.hasEmpty(studentModel)){
          throw   new BusinessException("数据不能为空", HttpStatus.NO_CONTENT.value());
        }
        super.save(studentModel);
    }

    @Override
    public PageResult online() {

        Set<String> Ids = redisService.getStudentIds();
        if (Ids.isEmpty()){
            return null;
        }
        List<String> newIds = new ArrayList<>(Ids.size());
        Ids.forEach(id -> {
            newIds.add(id.replace(RedisConstant.TOKEN_STUDENT_PREFIX , ""));
        });

        List<Student> students = this.lambdaQuery().in(Student::getSId, newIds).list();
        List<StudentModel> studentModels = new ArrayList<>();
        students.forEach(student -> {
            StudentModel studentModel = new StudentModel();
            BeanUtil.copyProperties(student , studentModel);
            Clazz clazz = classService.lambdaQuery().select(Clazz::getClassName,Clazz::getDId).eq(Clazz::getCId , student.getCId()).one();
            Department department = departmentService.lambdaQuery().select(Department::getDepartName).eq(Department::getDId, clazz.getDId()).one();
            studentModel.setClazz(clazz.getClassName());
            studentModel.setDepartment(department.getDepartName());
            studentModels.add(studentModel);
        });
        PageResult pageResult = new PageResult();
        pageResult.setItems(studentModels);
        pageResult.setTotal(Long.valueOf(newIds.size()));
        return pageResult;
    }

    @Override
    public void offline(String[] ids) {
        Arrays.stream(ids).forEach(id ->{
            String k = RedisConstant.TOKEN_STUDENT_PREFIX+id;
            if (redisService.exists(k)){
               redisService.remove(k);
            }
        });

    }


}
