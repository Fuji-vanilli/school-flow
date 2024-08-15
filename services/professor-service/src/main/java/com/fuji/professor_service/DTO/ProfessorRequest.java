package com.fuji.professor_service.DTO;

import jakarta.validation.constraints.NotNull;

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
        @NotNull(message = "class is required")
        List<String> classes,
        @NotNull(message = "course is required")
        List<String> courses,
        @NotNull(message = "degree is required")
        String degree,
        @NotNull(message = "phone number required!")
        String phone,
        @NotNull(message = "address required!")
        String address
) {
}
