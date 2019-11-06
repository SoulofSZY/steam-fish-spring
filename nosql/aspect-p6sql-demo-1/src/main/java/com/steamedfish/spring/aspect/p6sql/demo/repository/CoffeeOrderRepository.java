package com.steamedfish.spring.aspect.p6sql.demo.repository;

import com.steamedfish.spring.aspect.p6sql.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
