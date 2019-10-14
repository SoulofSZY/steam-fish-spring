package com.szy.spring.demo.dao;

import com.szy.spring.demo.model.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import sun.awt.im.SimpleInputMethodWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/9/15
 * @since 1.0.0
 */
@Slf4j
@Repository
public class FooDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData() {
        Arrays.asList("b", "c").forEach(bar -> {
            jdbcTemplate.update("insert into FOO (BAR) values (?)", bar);
        });

        Map<String, String> row = new HashMap<>(8);
        row.put("bar", "d");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("ID of d:{}", id.longValue());
    }

    public void listData() {
        log.info("COUNT:{}", jdbcTemplate.queryForObject("SELECT COUNT(1) FROM FOO", Long.class));
        List<String> list = jdbcTemplate.queryForList("SELECT BAR FROM FOO", String.class);
        list.forEach(bar -> log.info("bar:{}", bar));

        List<Foo> fooList = jdbcTemplate.query("SELECT * FROM FOO", (row, idx) -> {
            return Foo.builder().id(row.getLong(1))
                    .bar(row.getString(2))
                    .build();
        });

        fooList.forEach(foo -> log.info("foo:{}", foo.toString()));
    }

}
