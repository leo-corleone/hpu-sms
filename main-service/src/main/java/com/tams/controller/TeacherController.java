package com.tams.controller;

import com.tams.dto.AjaxResult;
import com.tams.dto.PageParam;
import com.tams.model.TeacherModel;
import com.tams.service.TeacherService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public AjaxResult getList(PageParam page){
        return AjaxResult.succ(teacherService.getList(page));
    }

    @PostMapping("/remove")
    public AjaxResult delete(@RequestBody Integer []ids){
        return AjaxResult.succ(teacherService.deleteTeacher(ids));
    }

    @PostMapping("/add")
    public AjaxResult add(@RequestBody TeacherModel teacherModel){
        return AjaxResult.succ(teacherService.save(teacherModel));
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody TeacherModel teacherModel){
        return  AjaxResult.succ(teacherService.updateById(teacherModel));
    }
}
