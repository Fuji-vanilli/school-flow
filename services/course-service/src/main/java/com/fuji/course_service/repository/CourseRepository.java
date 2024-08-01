package com.fuji.course_service.repository;

import com.fuji.course_service.entities.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course, String> {
    boolean existsByCode(String code);
    boolean existsByTitle(String title);
    Optional<Course> findByCode(String code);
    void deleteByCode(String code);
}
