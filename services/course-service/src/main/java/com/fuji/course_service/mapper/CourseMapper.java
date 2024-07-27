package com.fuji.course_service.mapper;

import com.fuji.course_service.DTO.CourseRequest;
import com.fuji.course_service.DTO.CourseResponse;
import com.fuji.course_service.entities.Course;

public interface CourseMapper {
    Course mapToCourse(CourseRequest request);
    CourseResponse mapToCourseResponse(Course course);
}
