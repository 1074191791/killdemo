package com.zhou.kill.killdemo.utils;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Component("redisLock")
@Slf4j
public class RedisLock implements Lock {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private DefaultRedisScript defaultRedisScript;

    private static final String key = "zhou";

    public final static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    /**
     * 100s过期时间
     */
    private static final Integer expire = 100000;

    /*//保证原子性LUA脚本
    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }*/



    /**
     * 阻塞式加锁实现
     */
    @SneakyThrows
    @Override
    public void lock() {
        if(this.tryLock()) {
            return;
        } else {
            Thread.sleep(10);
        }
        lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 非阻塞式加锁实现
     * @return
     */
    @Override
    public boolean tryLock() {
        String uuid = UUID.randomUUID().toString();
        threadLocal.set(uuid);
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, uuid, 20, TimeUnit.MILLISECONDS);
        return aBoolean;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        threadLocal.set(uuid);
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, uuid, time, unit);
        return aBoolean;
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        Object execute = redisTemplate.execute(defaultRedisScript, Arrays.asList(key), threadLocal.get());
        //System.out.println(execute);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
