package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.domain.SysConfig;
import com.tams.dto.AjaxResult;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.service.SysConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/3/19
 **/

@RestController
@RequestMapping("/sysconfig")
public class SysConfigController {

    @Resource
    private SysConfigService sysConfigService;

    @Permission(operation = OperationTypeEnum.R , right = RightTypeEnum.SYSCONFIG)
    @GetMapping("/list")
    public AjaxResult getList(){
        return AjaxResult.succ(sysConfigService.list());
    }

    @Permission(operation = OperationTypeEnum.R , right = RightTypeEnum.SYSCONFIG)
    @GetMapping("/configkey/{configkey}")
    public AjaxResult getByKey(@PathVariable String configkey){
        return AjaxResult.succ(sysConfigService.getByKey(configkey));
    }

    @Permission(operation = OperationTypeEnum.W , right = RightTypeEnum.SYSCONFIG)
    @PostMapping("/update")
    public AjaxResult updateSysConfig(@RequestBody SysConfig sysConfig){
        return AjaxResult.succ(sysConfigService.updateSysConfig(sysConfig));
    }

}
