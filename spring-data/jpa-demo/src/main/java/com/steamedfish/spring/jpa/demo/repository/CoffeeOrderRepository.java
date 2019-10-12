package com.steamedfish.spring.jpa.demo.repository;

import com.steamedfish.spring.jpa.demo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
