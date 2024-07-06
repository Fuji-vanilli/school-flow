package com.fuji.classservice.entities;

import com.fuji.classservice.models.Professor.Professor;
import com.fuji.classservice.models.Student.Student;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "class")
public class Class {
    @Id
    private String id;
    private Level level;
    private Section section;
    private BigDecimal numberOfStudents;
    private BigDecimal ecolage;
    private BigDecimal maximumCapacity;
    private List<Student> students= new ArrayList<>();
    private List<Professor> professors= new ArrayList<>();
}

