package com.steamedfish.spring.cache.demo.repository;

import com.steamedfish.spring.cache.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
