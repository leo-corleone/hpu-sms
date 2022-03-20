package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.jdbc.StringUtils;
import com.tams.base.redis.RedisService;
import com.tams.base.redis.util.RedisConstant;
import com.tams.domain.SysConfig;
import com.tams.enums.ResponseCode;
import com.tams.exception.base.BusinessException;
import com.tams.mapper.SysConfigMapper;
import com.tams.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/3/19
 **/

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper , SysConfig> implements SysConfigService {

    @Resource
    private RedisService redisService;

    @Override
    public SysConfig getByKey(String configKey) {
      return  this.lambdaQuery().eq(SysConfig::getConfigKey , configKey).one();
    }

    @Override
    public boolean updateSysConfig(SysConfig sysConfig) {

        if (ObjectUtil.isEmpty(sysConfig.getConfigKey()) || StringUtils.isNullOrEmpty(sysConfig.getConfigKey())){
            throw new BusinessException("config key 不能为空", ResponseCode.NoContent.getCode());
        }

        switch (sysConfig.getConfigKey()){
            case "sys:config:user:token":
               return   updateToken(sysConfig);
            case "":
               break;
        }
        return false;
    }



    public boolean updateToken(SysConfig sysConfig){

        this.lambdaUpdate().set(SysConfig::getConfigValue , sysConfig.getConfigValue())
                           .eq(SysConfig::getConfigKey , sysConfig.getConfigKey()).update();
        redisService.cacheValue(RedisConstant.USER_EXPIRE_TIME, sysConfig.getConfigValue());

        return true;
    }
}
