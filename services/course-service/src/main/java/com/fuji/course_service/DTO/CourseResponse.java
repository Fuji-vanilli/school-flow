package com.fuji.course_service.DTO;

import com.fuji.course_service.models.Professor;

import java.math.BigDecimal;

public record CourseResponse(
        String id,
        String code,
        String title,
        String description,
        String professorID,
        Professor professor,
        BigDecimal credit,
        BigDecimal hoursPerWeek,
        String syllabus
) {
}