package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.OnlineUser;
import com.tams.model.LoginModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @author swiChen
 * @date 2022/3/7
 **/
public interface OnlineUserService extends IService<OnlineUser> {

    void recordLogin(LoginModel login , HttpServletRequest request);


    Boolean offline(Integer[] ids);

}
