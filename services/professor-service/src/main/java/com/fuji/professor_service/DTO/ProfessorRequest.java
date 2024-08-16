package com.fuji.professor_service.DTO;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record ProfessorRequest(
        String id,
        String matricule,
        @NotNull(message = "firstname required")
        String firstname,
        String lastname,
        @NotNull(message = "date of birth required!")
        Date dateOfBirth,
        String birthPlace,
        @NotNull(message = "email is required!")
        String email,
        @NotNull(message = "genre is required")
        String genre,
        @NotNull(message = "class is required")
        List<String> classIDs,
        @NotNull(message = "course is required")
        List<String> courseIDs,
        @NotNull(message = "degree is required")
        String degree,
        @NotNull(message = "hours rate is required!")
        BigDecimal hoursRate,
        @NotNull(message = "phone number required!")
        String phone,
        @NotNull(message = "address required!")
        String address
) {
}
