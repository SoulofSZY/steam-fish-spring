package com.steamedfish.spring.redis.repository.demo.repository;

import com.steamedfish.spring.redis.repository.demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
