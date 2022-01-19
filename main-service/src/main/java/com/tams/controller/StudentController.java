package com.tams.controller;

import com.tams.domain.Student;
import com.tams.dto.AjaxResult;
import com.tams.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@RestController
@RequestMapping("/student/")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("{id}/query")
    public AjaxResult<Student> query(@PathVariable("id") Long sId){
        return AjaxResult.succ(studentService.getById(sId));
    }

}
