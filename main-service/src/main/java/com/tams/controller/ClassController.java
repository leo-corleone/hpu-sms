package com.tams.controller;

import com.tams.domain.Clazz;
import com.tams.dto.AjaxResult;
import com.tams.service.ClassService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

 @GetMapping("/getIdAndClassName")
 public AjaxResult<List<Clazz>> getIdAndClassName(){
   return AjaxResult.succ(classService.getIdAndClassName());
 }

}
