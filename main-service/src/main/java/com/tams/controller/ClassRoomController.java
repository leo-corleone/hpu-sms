package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.domain.ClassRoom;
import com.tams.dto.AjaxResult;
import com.tams.dto.PageParam;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.service.ClassRoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/3/14
 **/

@RestController
@RequestMapping("/classroom")
public class ClassRoomController {

    @Resource
    private ClassRoomService classRoomService;


    @Permission(operation = OperationTypeEnum.R , right = RightTypeEnum.CLASSROOM)
    @GetMapping("/list")
    public AjaxResult getList(PageParam pageParam){
      return AjaxResult.succ(classRoomService.getList(pageParam));
    }

    @Permission(operation = OperationTypeEnum.W , right = RightTypeEnum.CLASSROOM)
    @PostMapping("/update")
    public AjaxResult updateClassRoom(@RequestBody ClassRoom classRoom){
        return AjaxResult.succ(classRoomService.updateClassRoom(classRoom));
    }

    @Permission(operation = OperationTypeEnum.W , right = RightTypeEnum.CLASSROOM)
    @PostMapping("/add")
    public AjaxResult addClassRoom(@RequestBody ClassRoom classRoom){
        return AjaxResult.succ(classRoomService.addClassRoom(classRoom));
    }

}
