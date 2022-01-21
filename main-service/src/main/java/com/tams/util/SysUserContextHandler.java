package com.tams.util;

import com.tams.model.SysUser;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

public class SysUserContextHandler {

    private static ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    public static SysUser getSysUser(){
      return  threadLocal.get();
    }

    public static void setSysUser(SysUser user){
        threadLocal.set(user);
    }


}
