package com.fuji.course_service.services;

import com.fuji.course_service.DTO.CourseRequest;
import com.fuji.course_service.DTO.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseResponse create(CourseRequest request);
    CourseResponse getByCode(String code);
    CourseResponse update(CourseRequest request);
    List<CourseResponse> getAll();
    CourseResponse delete(String code);
}
