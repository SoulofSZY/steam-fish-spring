package com.steamedfish.spring.redis.demo.repository;

import com.steamedfish.spring.redis.demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
