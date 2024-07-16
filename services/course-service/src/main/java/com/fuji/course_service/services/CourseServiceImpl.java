package com.fuji.course_service.services;

import com.fuji.course_service.DTO.CourseRequest;
import com.fuji.course_service.DTO.CourseResponse;
import com.fuji.course_service.mapper.CourseMapper;
import com.fuji.course_service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponse create(CourseRequest request) {
        return null;
    }

    @Override
    public CourseResponse getByCode(String code) {
        return null;
    }

    @Override
    public CourseResponse update(CourseRequest request) {
        return null;
    }

    @Override
    public List<CourseResponse> getAll() {
        return List.of();
    }

    @Override
    public CourseResponse delete(String code) {
        return null;
    }
}
