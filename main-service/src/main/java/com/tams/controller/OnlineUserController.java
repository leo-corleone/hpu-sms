package com.tams.controller;

import com.tams.domain.OnlineUser;
import com.tams.dto.AjaxResult;
import com.tams.service.OnlineUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

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
    public AjaxResult offline(@RequestBody OnlineUser[] users){
        return AjaxResult.succ(onlineUserService.offline(Arrays.asList(users)));
    }

}
