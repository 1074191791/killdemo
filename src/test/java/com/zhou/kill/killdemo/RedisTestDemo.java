package com.zhou.kill.killdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;

@SpringBootTest
public class RedisTestDemo {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "redisLock")
    private Lock lock;

    @Test
    public void set(){
        redisTemplate.opsForValue().set("myKey","myValueaaa");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }


    @Test
    public void lockTest() {
        lock.tryLock();
    }

    @Test
    public void unLockTest() {
        boolean b = lock.tryLock();
        System.out.println(b);
        boolean b1 = lock.tryLock();
        System.out.println(b1);

        System.out.println("=================");
        lock.unlock();
    }
}
