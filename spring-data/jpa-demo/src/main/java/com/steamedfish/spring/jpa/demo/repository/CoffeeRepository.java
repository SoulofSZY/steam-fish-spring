package com.steamedfish.spring.jpa.demo.repository;

import com.steamedfish.spring.jpa.demo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
