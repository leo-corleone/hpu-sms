package com.tams.controller;

import com.tams.dto.AjaxResult;
import com.tams.model.LoginModel;
import com.tams.service.impl.SysLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@RestController
public class SysLoginController {

    @Resource
    private SysLoginService sysLoginService;

    @PostMapping("/login")
    public AjaxResult<Object> login(@RequestBody LoginModel login){

        Map<String , String> map = new HashMap<String , String>();
        String token = sysLoginService.login(login);
        map.put("token" , token);
        return AjaxResult.succ(map);
    }


}
