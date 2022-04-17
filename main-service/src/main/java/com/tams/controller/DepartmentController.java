package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.dto.AjaxResult;
import com.tams.dto.PageParam;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.model.DepartmentModel;
import com.tams.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @Permission(operation = OperationTypeEnum.R , right = RightTypeEnum.DEPARTMENT)
    @GetMapping("/list")
    public AjaxResult list(PageParam pageParam){
      return AjaxResult.succ(departmentService.getList(pageParam));
    }

    @GetMapping("/list/all")
    public AjaxResult getAll(){
        return AjaxResult.succ(departmentService.list());
    }

    @GetMapping("/teacher/list")
    public AjaxResult departmentTeacher(){
        return AjaxResult.succ(departmentService.departmentTeacher());
    }

    @GetMapping("/class/list")
    public AjaxResult departmentClass(){
        return AjaxResult.succ(departmentService.departmentTeacher());
    }

    @Permission(operation = OperationTypeEnum.W , right = RightTypeEnum.DEPARTMENT)
    @PostMapping("/update")
    public AjaxResult updateDepartment(@RequestBody DepartmentModel departmentModel){
        return AjaxResult.succ(departmentService.updateDepartment(departmentModel));
    }

    @Permission(operation = OperationTypeEnum.W , right = RightTypeEnum.DEPARTMENT)
    @PostMapping("/add")
    public AjaxResult addDepartment(@RequestBody DepartmentModel departmentModel){
        return AjaxResult.succ(departmentService.addDepartment(departmentModel));
    }
}
