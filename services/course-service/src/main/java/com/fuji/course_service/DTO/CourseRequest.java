package com.fuji.course_service.DTO;

import com.fuji.course_service.models.Professor;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CourseRequest(
        String id,
        @NotNull(message = "code of course required!")
        String code,
        @NotNull(message = "title of the course required!")
        String title,
        String description,
        String professorID,
        Professor professor,
        @NotNull(message = "credit of the course required!")
        BigDecimal credit,
        @NotNull(message = "hours per week of the course required!")
        BigDecimal hoursPerWeek,
        String syllabus
) {
}
