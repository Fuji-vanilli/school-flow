package com.fuji.student_service.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "student")
public class Student {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String email;
    private String phone;
    private Address address;
    private String imageUrl;
    private Class aClass;
    private Instant createdDate;
    private Instant lastUpdatedDate;
}
