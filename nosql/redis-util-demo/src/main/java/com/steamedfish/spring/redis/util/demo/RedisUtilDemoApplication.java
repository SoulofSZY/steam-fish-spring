package com.steamedfish.spring.redis.util.demo;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisUtilDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(RedisUtilDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
