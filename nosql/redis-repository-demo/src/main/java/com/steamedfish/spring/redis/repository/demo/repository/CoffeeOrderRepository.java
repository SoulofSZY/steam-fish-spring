package com.steamedfish.spring.redis.repository.demo.repository;

import com.steamedfish.spring.redis.repository.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
