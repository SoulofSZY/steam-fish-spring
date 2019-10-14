package com.szy.spring.demodruiddatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

/**
 * @author steamedfish
 */
@SpringBootApplication
@Slf4j
public class DemoDruidDatasourceApplication implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DemoDruidDatasourceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        log.info(dataSource.toString());
    }
}
