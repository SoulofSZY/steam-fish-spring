package com.steamedfish.spring.redis.util.demo;

import com.steamedfish.spring.redis.util.demo.helper.RedisHelper;
import com.steamedfish.spring.redis.util.demo.service.TestCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class RedisUtilDemoApplication implements ApplicationRunner {

    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private TestCacheService cacheService;

    public static void main(String[] args) {
        SpringApplication.run(RedisUtilDemoApplication.class, args);
    }

    @Bean
    public RedisHelper redisHelper(StringRedisTemplate stringRedisTemplate) {
        return  new RedisHelper(stringRedisTemplate, "test");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        IntStream.range(1,10).forEach(i->{
            log.info(cacheService.testInnerApp("id_1"));
        });
       // redisHelper.set("szy01", "test");
       // log.info(redisHelper.get("szy01"));
    }


}
