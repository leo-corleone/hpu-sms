package com.tams.controller;

import com.tams.dto.AjaxResult;
import com.tams.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/2/20
 **/

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @GetMapping("/list/all")
    public AjaxResult getAll(){
        return AjaxResult.succ(teacherService.getAll());
    }
}
