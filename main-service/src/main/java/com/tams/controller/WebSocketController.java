package com.tams.controller;

import com.tams.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swiChen
 * @date 2022/4/16
 **/

@RestController
@RequestMapping("/ws")
public class WebSocketController {

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${server.port}")
    private String port;

    @GetMapping("/url")
    public AjaxResult getURL(){
        String url = "ws://";
        contextPath = contextPath.replace("//" ,"/");
        if (profile.equals("dev")){
            url += "127.0.0.1:"+port+contextPath+"ws/";
        }else {
            url += "119.23.60.23:"+port+contextPath+"ws/";
        }
        return AjaxResult.succ((Object) url);
    }

}
