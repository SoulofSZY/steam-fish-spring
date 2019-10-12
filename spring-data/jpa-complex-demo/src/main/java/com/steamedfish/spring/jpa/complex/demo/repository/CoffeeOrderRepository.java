package com.steamedfish.spring.jpa.complex.demo.repository;

import com.steamedfish.spring.jpa.complex.demo.model.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);
    List<CoffeeOrder> findByItems_Name(String name);
}
