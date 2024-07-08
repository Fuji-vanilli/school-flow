package com.fuji.classservice.DTO;

import com.fuji.classservice.entities.Section;
import com.fuji.classservice.models.Professor;
import com.fuji.classservice.models.Student;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ClassResponse(
        String id,
        String level,
        Section section,
        BigDecimal maximumCapacity,
        BigDecimal numberOfStudents,
        BigDecimal ecolage,
        List<Student> students,
        List<Professor> professors,
        Instant createdDate,
        Instant lastModifiedDate
) {
}
