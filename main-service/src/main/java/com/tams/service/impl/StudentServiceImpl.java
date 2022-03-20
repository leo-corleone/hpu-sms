package com.tams.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tams.base.redis.RedisService;
import com.tams.domain.Student;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.enums.ResponseCode;
import com.tams.exception.base.BusinessException;
import com.tams.mapper.StudentMapper;
import com.tams.model.StudentModel;
import com.tams.service.ClassService;
import com.tams.service.DepartmentService;
import com.tams.service.ImageService;
import com.tams.service.StudentService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


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
        if (StringUtils.isEmpty(model.getOrder()) || StringUtils.isEmpty(model.getSortBy())){
            model.setOrder("asc");
            model.setSortBy("s_id");
        }
        PageResult<List<StudentModel>> pageResult = new PageResult<>();
        pageResult.setItems(studentMapper.getStudentAll(model));
        pageResult.setTotal(objects.getTotal());
        return pageResult;
    }

    @Override
    public void remove(Long[] ids) {
        if (ObjectUtil.isEmpty(ids) || ids.length == 0){
           throw  new BusinessException("请求数据不能为空", ResponseCode.NoContent.code);
        }
        removeByIds(Arrays.asList(ids));
    }

    @Override
    public void add(StudentModel studentModel) {
        if (ObjectUtil.isEmpty(studentModel) || ObjectUtil.hasEmpty(studentModel)){
          throw   new BusinessException("数据不能为空", ResponseCode.NoContent.code);
        }
        super.save(studentModel);
    }

    @Override
    public void update(Student student) {
        if (ObjectUtil.isEmpty(student)){
            throw new BusinessException("学生信息不能为空",ResponseCode.NoContent.code);
        }
        this.updateById(student);
    }


}
