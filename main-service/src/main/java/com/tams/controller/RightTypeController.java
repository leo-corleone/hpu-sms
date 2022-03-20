package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.dto.AjaxResult;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.service.RightTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/3/18
 **/

@RestController
@RequestMapping("/righttype")
public class RightTypeController {

    @Resource
    private RightTypeService rightTypeService;


    @Permission(operation = OperationTypeEnum.R , right = RightTypeEnum.CONFIG)
    @GetMapping("/list")
    public AjaxResult getList(){
        return AjaxResult.succ(rightTypeService.getList());
    }

}
