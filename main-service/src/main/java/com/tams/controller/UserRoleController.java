package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.dto.AjaxResult;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.model.UserRightModel;
import com.tams.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/3/18
 **/

@RequestMapping("/userrole")
@RestController
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @Permission(operation = OperationTypeEnum.R , right = RightTypeEnum.CONFIG)
    @GetMapping("/right")
    public AjaxResult getUserRight(UserRightModel userRightModel){
        return AjaxResult.succ(userRoleService.getRight(userRightModel));
    }

    @Permission(operation = OperationTypeEnum.W , right = RightTypeEnum.CONFIG)
    @PostMapping("/update")
    public AjaxResult update(@RequestBody UserRightModel userRightModel){
        return AjaxResult.succ(userRoleService.updateRight(userRightModel));
    }

}
