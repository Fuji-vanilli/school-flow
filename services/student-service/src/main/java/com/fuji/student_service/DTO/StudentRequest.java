package com.fuji.student_service.DTO;

import com.fuji.student_service.entities.Address;
import com.fuji.student_service.models.Class;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public record StudentRequest (
        String id,
        String firstname,
        String lastname,
        Date dateOfBirth,
        String birthPlace,
        String email,
        String phone,
        Address address,
        String imageUrl,
        Class aClass,
        List<Ecolage>ecolages,
        Note note,
        String originSchool,
        Instant createdDate,
        Instant lastUpdatedDate
){
}
