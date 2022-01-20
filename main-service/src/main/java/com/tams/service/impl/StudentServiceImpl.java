package com.tams.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tams.domain.Student;
import com.tams.mapper.StudentMapper;
import com.tams.dto.PageParam;
import com.tams.model.StudentModel;
import com.tams.service.ImageService;
import com.tams.service.StudentService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
                            implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ImageService imageService;

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
    public List<StudentModel> getStudents(PageParam model) {

        Page<StudentModel> page = PageHelper.startPage(model.getPage(), model.getPageSize() , model.getOrder());

        return null;
    }
}
