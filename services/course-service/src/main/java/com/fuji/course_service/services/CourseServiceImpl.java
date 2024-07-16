package com.fuji.course_service.services;

import com.fuji.course_service.DTO.CourseRequest;
import com.fuji.course_service.DTO.CourseResponse;
import com.fuji.course_service.entities.Course;
import com.fuji.course_service.mapper.CourseMapper;
import com.fuji.course_service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponse create(CourseRequest request) {
        if (courseRepository.existsByCode(request.code())) {
            log.error("sorry, course already exists");
            throw new IllegalArgumentException("sorry, course already exists");
        }

        Course course = courseMapper.mapToCourse(request);
        course.setId(UUID.randomUUID().toString());
        course.setCreatedDate(Instant.now());

        courseRepository.save(course);
        log.info("new course created successfully");

        return courseMapper.mapToCourseResponse(course);
    }

    @Override
    public CourseResponse getByCode(String code) {
        return null;
    }

    @Override
    public CourseResponse update(CourseRequest request) {

        return null;
    }

    private void mergeCourse(Course course, CourseRequest request) {
        if (!request.code().isBlank()) {
            course.setCode(request.code());
        }
        if (!request.description().isBlank()) {
            course.setDescription(request.description());
        }
        if (!request.title().isBlank()) {
            course.setTitle(request.title());
        }
        if (!Objects.isNull(request.credit())) {
            course.setCredit(request.credit());
        }
        if (!Objects.isNull(request.hoursPerWeek())) {
            course.setHoursPerWeek(request.hoursPerWeek());
        }
        if (!request.syllabus().isBlank()) {
            course.setSyllabus(request.syllabus());
        }
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
