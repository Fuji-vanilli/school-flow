package com.fuji.classservice.models;

import java.time.Instant;
import java.util.Date;

public record Student(
        String id,
        String matricule,
        String firstname,
        String lastname,
        Date dateOfBirth,
        String birthPlace,
        String genre,
        String email,
        String phone,
        String address,
        String imageUrl,
        String originSchool,
        Instant createdDate,
        Instant lastUpdatedDate
) {
}
