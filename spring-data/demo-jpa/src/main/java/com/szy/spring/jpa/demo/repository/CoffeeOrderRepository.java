/**
 * Copyright (C), 2019-2019
 */
package com.szy.spring.jpa.demo.repository;

import com.szy.spring.jpa.demo.model.CoffeeOrder;

import java.util.List;

/**
 * 〈order rp〉
 *
 * @author steamedfish
 * @create 2019/9/22
 * @since 1.0.0
 */
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_Name(String name);
}
