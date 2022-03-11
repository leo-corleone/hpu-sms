package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.domain.Student;
import com.tams.dto.AjaxResult;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.model.StudentModel;
import com.tams.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @Permission(operation = OperationTypeEnum.R , right = RightTypeEnum.STUDENT)
    @GetMapping("/{id}/query")
    public AjaxResult<Student> query(@PathVariable("id") Long sId){
        return AjaxResult.succ(studentService.getById(sId));
    }

    @GetMapping("/list")
    public AjaxResult<PageResult> getList(PageParam page){

        return AjaxResult.succ(studentService.getStudents(page));
    }

    @PostMapping("/remove")
    public AjaxResult<Void> remove(@RequestBody Long []ids){
        studentService.remove(ids);
        return AjaxResult.succ(null);
    }

    @PostMapping("/add")
    public AjaxResult<Void> add(@RequestBody StudentModel studentModel){
        studentService.add(studentModel);
        return AjaxResult.succ("ok");
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody Student student){
        studentService.update(student);
        return AjaxResult.succ("ok");
    }


}
