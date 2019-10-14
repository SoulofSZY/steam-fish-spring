package com.szy.spring.demo.model;

import lombok.Builder;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/9/15
 * @since 1.0.0
 */
@Data
@Builder
public class Foo {

    private Long id;
    private String bar;
}
