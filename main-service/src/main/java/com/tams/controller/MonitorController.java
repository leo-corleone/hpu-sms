package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.domain.Monitor;
import com.tams.dto.AjaxResult;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.service.MonitorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author swiChen
 * @date 2022/3/8
 **/

@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Resource
    private MonitorService monitorService;


    @Permission(operation = OperationTypeEnum.R , right = RightTypeEnum.MONITOR)
    @GetMapping("/list")
    public AjaxResult getList(){

        return AjaxResult.succ(monitorService.list());
    }

    @Permission(operation = OperationTypeEnum.W , right = RightTypeEnum.MONITOR)
    @PostMapping("/offline")
    public AjaxResult offline(@RequestBody Monitor[] users){
        return AjaxResult.succ(monitorService.offline(Arrays.asList(users)));
    }

    @DeleteMapping("/offline")
    public AjaxResult offline(){
        return AjaxResult.succ(monitorService.offline());
    }

}
