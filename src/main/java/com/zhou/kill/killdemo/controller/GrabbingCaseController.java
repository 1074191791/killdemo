package com.zhou.kill.killdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 抢案
 *
 * @author zhous
 * @version 1.0
 * @date 2021/7/7 10:55
 */
@RestController
@RequestMapping("/channelDistribution")
public class GrabbingCaseController {

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @PostMapping("/redisTestDemo/{key}/{val}")
    public String redisTestDemo(@PathVariable("key") String key, @PathVariable("val") String value) {
        System.out.println("key: " + key + "; ----- val:" + value);
        redisTemplate.opsForValue().set(key, value, 1, TimeUnit.MINUTES);
        return redisTemplate.opsForValue().get(key);
    }


}
