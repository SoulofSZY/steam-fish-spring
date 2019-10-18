package com.steamedfish.spring.mongo.repository.demo.repository;

import com.steamedfish.spring.mongo.repository.demo.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CoffeeRepository extends MongoRepository<Coffee, String> {
    List<Coffee> findByName(String name);
}
