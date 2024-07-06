package com.fuji.classservice.DTO;

import com.fuji.classservice.entities.Level;
import com.fuji.classservice.entities.Section;
import com.fuji.classservice.models.Professor.Professor;
import com.fuji.classservice.models.Student.Student;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ClassResponse(
        String id,
        Level level,
        Section section,
        BigDecimal maximumCapacity,
        BigDecimal numberOfStudents,
        BigDecimal ecolage,
        List<Student> students,
        List<Professor> professors
) {
}
