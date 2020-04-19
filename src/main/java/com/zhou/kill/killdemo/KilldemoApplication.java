package com.zhou.kill.killdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhou.kill.killdemo.mapper")
public class KilldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KilldemoApplication.class, args);
    }

}
