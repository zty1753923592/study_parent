package com.zty.study.cache;

import com.zty.study.commons.config.Constants;
import com.zty.study.commons.exception.StudyBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类  采用StringRedisTemplate操作redis，RedisTemplate要自己实现序列化方式，我懒得写了，你要弄自己写
 */
@Component
public class RedisUtils {

    private static StringRedisTemplate template;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 因为将StringRedisTemmplate申明成静态了，所以template注入不了，智能通过再申明一个实列，然后再将他赋值
     */
    @PostConstruct
    public void init() {
        template = redisTemplate;
    }


    public static void set(String key, String value) {
        try {
            template.opsForValue().set(key, value);
        }catch (Exception ex) {
            //全都使用自己定义的异常进行抛出,可以放入提示信息和自定义的状态码
            throw new StudyBaseException(Constants.StatusType.REDIS_ERROR.getCode(), Constants.StatusType.REDIS_ERROR.getMsg());
        }
    }

    /**
     * 存入缓存
     * @param key
     * @param value
     */
    public void setMap(String key, String value) {
        template.opsForValue().set(key, value);
    }

    /**
     * 设置过期时间
     * @param key
     */
    public void expire(String key) {
        template.expire(key,60, TimeUnit.SECONDS);
    }

    /**
     * 根据key获取
     * @param key
     * @return value
     */
    public String getMap(String key) {
        return template.opsForValue().get(key);
    }

    /**
     * 删除指定key
     * @param key
     * @return
     */
    public boolean delMap(String key) {
        return template.delete(key);
    }




}
