package com.fuji.course_service.services;

import com.fuji.course_service.DTO.CourseRequest;
import com.fuji.course_service.DTO.CourseResponse;
import com.fuji.course_service.entities.Course;
import com.fuji.course_service.mapper.CourseMapper;
import com.fuji.course_service.repository.CourseRepository;
import com.fuji.course_service.webClient.WebClientClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final WebClientClass webClientCourse;

    @Override
    public CourseResponse create(CourseRequest request) {
        /*if (courseRepository.existsByTitle(request.title())) {
            log.error("sorry, course already exists");
            throw new IllegalArgumentException("sorry, course already exists");
        }
*/
        Course course = courseMapper.mapToCourse(request);
        course.setId(UUID.randomUUID().toString());
        course.setCreatedDate(Instant.now());

        courseRepository.save(course);
        log.info("new course created successfully");

        webClientCourse.addCourseToClass(course.getId(), request.classID());
        log.info("the new course is added to class");
        return courseMapper.mapToCourseResponse(course);
    }

    @Override
    public CourseResponse getByCode(String code) {
        Optional<Course> courseByCode = courseRepository.findByCode(code);
        if (courseByCode.isEmpty()) {
            log.error("course with code: {} not found", code);
            throw new IllegalArgumentException("course not found");
        }

        Course course = courseByCode.get();

        log.info("course with code: {} getted successfully", code);
        return courseMapper.mapToCourseResponse(course);
    }

    @Override
    public CourseResponse update(CourseRequest request) {
        Optional<Course> courseByCode = courseRepository.findByCode(request.code());
        if (courseByCode.isEmpty()) {
            log.error("course not found");
            throw new IllegalArgumentException("course not found");
        }

        Course course = courseByCode.get();
        mergeCourse(course, request);
        course.setUpdatedDate(Instant.now());

        courseRepository.save(course);
        log.info("update course successfully");

        return courseMapper.mapToCourseResponse(course);
    }

    @Override
    public List<CourseResponse> getAllByIDs(List<String> ids) {
        log.info("all course by ids getted successfully!");
        return courseRepository.findAllById(ids).stream()
                .map(courseMapper::mapToCourseResponse)
                .toList();
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
        log.info("all course getted successfully");
        return courseRepository.findAll().stream()
                .map(courseMapper::mapToCourseResponse)
                .toList();
    }

    @Override
    public CourseResponse delete(String code) {
        Optional<Course> courseByCode = courseRepository.findByCode(code);
        if (courseByCode.isEmpty()) {
            log.error("course with the code: {} not found", code);
            throw new IllegalArgumentException("course not found");
        }

        courseRepository.deleteByCode(code);
        log.info("delete course successfully");
        return courseMapper.mapToCourseResponse(courseByCode.get());
    }
}
