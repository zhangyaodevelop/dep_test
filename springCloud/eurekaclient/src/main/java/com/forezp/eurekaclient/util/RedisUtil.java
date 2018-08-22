package com.forezp.eurekaclient.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Title RedisUtil
 * @Description ${DESCRIPTION}
 * @Author Yao.Zhang
 * @date: 2018/7/10
 */
@Component
public class RedisUtil {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 根据key和value保存到redis
     * @param key
     * @param value
     */
    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key,value);
    }


    /**
     * 根据key获取指定redis中的值
     * @param key
     * @return
     */
    public String getValue(String key){
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 保存有过期时间的值
     * @param key
     * @param value
     * @param second
     */
    public void setKeyWithExpire(String key,String value,int second){
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        ops.set(key,value,second, TimeUnit.SECONDS);
    }

    /**
     * 设置自增
     * @param key
     * @param value
     */
    public void increment(String key,int value){
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        ops.increment(key,value);
    }


    /**
     * 获取指定key的过期时间
     * @param key
     * @param timeUnit
     * @return
     */
    public Long getKeyExpire(String key,TimeUnit timeUnit){
        return redisTemplate.getExpire(key,timeUnit);
    }


}
