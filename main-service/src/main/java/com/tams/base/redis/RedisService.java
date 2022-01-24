package com.tams.base.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Service
public class RedisService {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    public void set(String k , String v){
        redisTemplate.opsForValue().set(k,v);
    }

    public void set(String k , String v , TimeUnit unit , Long time){
        redisTemplate.opsForValue().set(k,v,time,unit);
    }

    public String get(String k){
      return redisTemplate.opsForValue().get(k);
    }

    public void lpush(String k , String ...v){
        redisTemplate.opsForList().leftPushAll(k ,v);
    }

    public String lpop(String k){
        return redisTemplate.opsForList().leftPop(k);
    }

    public List<String> lrange(String k , Long start , Long end){
       return redisTemplate.opsForList().range(k,start ,end);
    }

    public boolean exists(String k){
       return Boolean.TRUE.equals(redisTemplate.hasKey(k));
    }



}
