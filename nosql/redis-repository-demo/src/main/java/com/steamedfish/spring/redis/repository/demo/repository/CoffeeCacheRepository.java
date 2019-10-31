package com.steamedfish.spring.redis.repository.demo.repository;

import com.steamedfish.spring.redis.repository.demo.model.CoffeeCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoffeeCacheRepository extends CrudRepository<CoffeeCache, Long> {
    Optional<CoffeeCache> findOneByName(String name);
}
