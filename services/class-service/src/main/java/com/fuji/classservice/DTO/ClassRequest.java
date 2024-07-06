package com.fuji.classservice.DTO;

import com.fuji.classservice.models.Professor.Professor;
import com.fuji.classservice.models.Student.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record ClassRequest(
        @N
        String level,
        String section,
        BigDecimal numberOfStudents,
        List<Student>students,
        List<Professor> professors
) {
}
