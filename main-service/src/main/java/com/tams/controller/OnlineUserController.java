package com.tams.controller;

import com.tams.dto.AjaxResult;
import com.tams.service.OnlineUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/3/8
 **/

@RestController
@RequestMapping("/onlineuser")
public class OnlineUserController {

    @Resource
    private OnlineUserService onlineUserService;

    @GetMapping("/list")
    public AjaxResult getList(){
        return AjaxResult.succ(onlineUserService.list());
    }

    @PostMapping("/offline")
    public AjaxResult offline(Integer[] ids){
        return AjaxResult.succ(onlineUserService.offline(ids));
    }

}
