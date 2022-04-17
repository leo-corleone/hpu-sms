package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.dto.AjaxResult;
import com.tams.dto.PageParam;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.model.PasswordModel;
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

    @Permission(operation = OperationTypeEnum.R, right = RightTypeEnum.TEACHER)
    @GetMapping("/list")
    public AjaxResult getList(PageParam page){
        return AjaxResult.succ(teacherService.getList(page));
    }

    @GetMapping("/list/all")
    public AjaxResult getAll(PageParam page){
        return AjaxResult.succ(teacherService.list());
    }

    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id){
        return AjaxResult.succ(teacherService.getOneId(id));
    }

    @Permission(operation = OperationTypeEnum.W, right = RightTypeEnum.TEACHER)
    @PostMapping("/remove")
    public AjaxResult delete(@RequestBody Integer []ids){
        return AjaxResult.succ(teacherService.deleteTeacher(ids));
    }

    @Permission(operation = OperationTypeEnum.W, right = RightTypeEnum.TEACHER)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody TeacherModel teacherModel){
        return AjaxResult.succ(teacherService.save(teacherModel));
    }

    @Permission(operation = OperationTypeEnum.W, right = RightTypeEnum.TEACHER)
    @PostMapping("/update")
    public AjaxResult update(@RequestBody TeacherModel teacherModel){
        return  AjaxResult.succ(teacherService.updateById(teacherModel));
    }

    @PostMapping("/update/profile")
    public AjaxResult updateProfile(@RequestBody TeacherModel teacherModel){
        return  AjaxResult.succ(teacherService.updateById(teacherModel));
    }

    @PostMapping("/update/pwd")
    public AjaxResult updatePwd(@RequestBody PasswordModel passwordModel){
        return  AjaxResult.succ(teacherService.updatePwd(passwordModel));
    }
}
