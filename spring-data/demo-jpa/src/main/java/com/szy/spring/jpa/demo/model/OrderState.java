package com.szy.spring.jpa.demo.model;

/**
 * 〈订单状态枚举〉
 *
 * @author steamedfish
 * @create 2019/9/22
 * @since 1.0.0
 */
public enum OrderState {
    INIT, PAID, BREWING, BREWED, TAKEN, CANCELLED;
}
