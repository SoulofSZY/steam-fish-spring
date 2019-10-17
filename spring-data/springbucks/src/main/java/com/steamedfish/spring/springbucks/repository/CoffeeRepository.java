package com.steamedfish.spring.springbucks.repository;

import com.steamedfish.spring.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
