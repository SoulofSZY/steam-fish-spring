package com.steamedfish.spring.aspect.p6sql.demo.repository;

import com.steamedfish.spring.aspect.p6sql.demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
