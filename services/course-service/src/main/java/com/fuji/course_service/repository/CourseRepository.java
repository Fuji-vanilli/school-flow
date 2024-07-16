package com.fuji.course_service.repository;

import com.fuji.course_service.entities.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
