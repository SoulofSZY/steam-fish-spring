package com.szy.spring.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.annotation.Target;

/**
 * @author steamedfish
 */
@SpringBootApplication
@Slf4j
public class DemoProgramTransactionApplication implements CommandLineRunner {

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoProgramTransactionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("count:{}", getCount());
        transactionTemplate.execute(transactionStatus -> {
            jdbcTemplate.execute("insert into foo (id, bar) values (1, 'a')");
            log.info("count:{}", getCount());
            transactionStatus.setRollbackOnly();
            return getCount();
        });
        log.info("count:{}", getCount());
    }

    private long getCount() {
        return (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM FOO")
                .get(0).get("CNT");
    }
}
