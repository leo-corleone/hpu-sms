package com.tams.controller;

import com.tams.dto.AjaxResult;
import com.tams.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@RestController
@RequestMapping("/department/")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @GetMapping("list")
    public AjaxResult list(){
      return AjaxResult.succ(departmentService.list());
    }


}
