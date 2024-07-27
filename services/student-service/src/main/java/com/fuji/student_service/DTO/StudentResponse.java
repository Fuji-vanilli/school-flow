package com.fuji.student_service.DTO;

import com.fuji.student_service.entities.Address;
import com.fuji.student_service.models.Class;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;
import com.fuji.student_service.models.Report;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public record StudentResponse(
        String id,
        String matricule,
        String firstname,
        String lastname,
        Date dateOfBirth,
        String birthPlace,
        String email,
        String phone,
        String address,
        String imageUrl,
        String classID,
        Class aClass,
        List<Ecolage> ecolages,
        Note note,
        Report report,
        String originSchool,
        Instant createdDate,
        Instant lastUpdatedDate
) {
}
