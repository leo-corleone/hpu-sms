package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.SysConfig;

/**
 * @author swiChen
 * @date 2022/3/19
 **/
public interface SysConfigService extends IService<SysConfig> {

    boolean updateSysConfig(SysConfig sysConfig);

    SysConfig getByKey(String configKey);

}
