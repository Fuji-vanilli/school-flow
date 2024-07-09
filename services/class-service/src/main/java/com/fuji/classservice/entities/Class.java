package com.fuji.classservice.entities;

import com.fuji.classservice.models.Professor;
import com.fuji.classservice.models.Student;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "class")
public class Class {
    @Id
    private String id;
    private String level;
    private Section section;
    private BigDecimal numberOfStudents;
    private BigDecimal ecolage;
    private BigDecimal maximumCapacity;
    private List<String> studentsID= new ArrayList<>();
    private List<Student> students= new ArrayList<>();
    private List<Professor> professors= new ArrayList<>();
    private Instant createdDate;
    private Instant lastModifiedDate;
}

