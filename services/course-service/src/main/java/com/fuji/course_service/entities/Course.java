package com.fuji.course_service.entities;

import com.fuji.course_service.models.Professor;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "course")
public class Course {
    @Id
    private String id;
    private String code;
    private String title;
    private String description;
    private String professorID;
    private Professor professor;
    private BigDecimal credit;
    private BigDecimal hoursPerWeek;
    private BigDecimal pricePerHour;
    private String syllabus;
    private Instant createdDate;
    private Instant updatedDate;
}
