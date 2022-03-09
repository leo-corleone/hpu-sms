package com.tams.base.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Service
public class RedisService {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;


    public void cacheHash(String k , String field , String value){
        redisTemplate.opsForHash().put(k , field , value);
    }

    public String getCacheHash(String k , String field){
        return (redisTemplate.opsForHash().get(k, field)).toString();
    }

    public Boolean expire(String k , TimeUnit unit , Long time ){
       return redisTemplate.expire(k ,time , unit);
    }

    public void cacheValue(String k , String v){
        redisTemplate.opsForValue().set(k,v);
    }

    public void cacheValue(String k , String v , TimeUnit unit , Long time){
        redisTemplate.opsForValue().set(k,v,time,unit);
    }

    public String getValue(String k){
      return redisTemplate.opsForValue().get(k);
    }



    public boolean exists(String k){
       return Boolean.TRUE.equals(redisTemplate.hasKey(k));
    }

    public boolean remove(String k){
        return Boolean.TRUE.equals(redisTemplate.delete(k));
    }

}
