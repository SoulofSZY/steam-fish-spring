package com.steamedfish.spring.simple.controller.demo.repository;

import com.steamedfish.spring.simple.controller.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
