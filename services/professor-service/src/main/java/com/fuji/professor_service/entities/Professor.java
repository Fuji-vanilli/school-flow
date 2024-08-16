package com.fuji.professor_service.entities;

import com.fuji.professor_service.models.Class;
import com.fuji.professor_service.models.Course;
import com.fuji.professor_service.models.CourseApply;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "professor")
public class Professor {
    private String id;
    private String matricule;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String birthPlace;
    private String email;
    private String phone;
    private String address;
    private String imageUrl;
    private String degree;
    private String genre;
    private BigDecimal hoursRate;
    private List<String> classIDs;
    private List<String> courseIDs;
    private List<CourseApply> courseApplies= new ArrayList<>();
    private Instant createdDate;
    private Instant lastUpdatedDate;
    @Transient
    private List<Class> classes= new ArrayList<>();
    @Transient
    private List<Course> courses= new ArrayList<>();
}
