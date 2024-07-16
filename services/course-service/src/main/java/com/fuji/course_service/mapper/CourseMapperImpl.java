package com.fuji.course_service.mapper;

import com.fuji.course_service.DTO.CourseRequest;
import com.fuji.course_service.DTO.CourseResponse;
import com.fuji.course_service.entities.Course;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseMapperImpl implements CourseMapper{
    @Override
    public Course mapToCourse(CourseRequest request) {
        return Course.builder()
                .id(request.id())
                .code(request.code())
                .title(request.title())
                .description(request.description())
                .credit(request.credit())
                .hoursPerWeek(request.hoursPerWeek())
                .syllabus(request.syllabus())
                .build();
    }

    @Override
    public CourseResponse mapToCourseResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getCode(),
                course.getTitle(),
                course.getDescription(),
                course.getProfessorID(),
                course.getProfessor(),
                course.getCredit(),
                course.getHoursPerWeek(),
                course.getSyllabus()
        );
    }
}
