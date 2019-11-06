package com.steamedfish.spring.simple.controller.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableCaching
public class SimpleControllerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleControllerDemoApplication.class, args);
    }

}
