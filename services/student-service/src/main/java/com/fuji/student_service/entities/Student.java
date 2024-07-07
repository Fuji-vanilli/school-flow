package com.fuji.student_service.entities;

import com.fuji.student_service.models.Class;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;
import com.fuji.student_service.models.Report;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "student")
public class Student {
    @Id
    private String id;
    private String matricule;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String birthPlace;
    private String email;
    private String phone;
    private Address address;
    private String imageUrl;
    private Class aClass;
    private List<Ecolage> ecolages;
    private Note note;
    private Report report;
    private String originSchool;
    private Instant createdDate;
    private Instant lastUpdatedDate;
}
