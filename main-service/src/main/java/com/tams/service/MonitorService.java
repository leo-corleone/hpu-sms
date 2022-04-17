package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Monitor;
import com.tams.model.LoginModel;

import java.util.List;

/**
 * @author swiChen
 * @date 2022/3/7
 **/
public interface MonitorService extends IService<Monitor> {

    void recordLogin(LoginModel login );

    Boolean offline(List<Monitor> users);

    Boolean offline();

    Boolean autoOffline();

}
