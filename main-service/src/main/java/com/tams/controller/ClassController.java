package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.dto.AjaxResult;
import com.tams.dto.PageParam;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.model.ClassModel;
import com.tams.service.ClassService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swiChen
 * @date 2022/2/12
 **/

@RestController
@RequestMapping("/class")
public class ClassController {

 @Resource
 private ClassService classService;

 @Permission(right = RightTypeEnum.CLASS , operation = OperationTypeEnum.R)
 @GetMapping("/list")
 public AjaxResult<List<ClassModel>> getList(PageParam pageParam){
   return AjaxResult.succ(classService.getList(pageParam));
 }

 @Permission(right = RightTypeEnum.CLASS , operation = OperationTypeEnum.W)
 @PostMapping("/update")
 public AjaxResult updateClass(@RequestBody ClassModel classModel){
   return AjaxResult.succ(classService.updateById(classModel));
 }

 @Permission(right = RightTypeEnum.CLASS , operation = OperationTypeEnum.W)
 @PostMapping("/add")
 public AjaxResult addClass(@RequestBody ClassModel classModel){
  return AjaxResult.succ(classService.save(classModel));
 }


}
