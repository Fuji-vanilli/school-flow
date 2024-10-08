package com.fuji.classservice.repository;

import com.fuji.classservice.entities.Class;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassRepository extends MongoRepository<Class, String> {
    boolean existsByLevel(String level);
}
