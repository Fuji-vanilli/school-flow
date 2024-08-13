package com.fuji.professor_service.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ProfessorRequest(
        String id,
        String matricule,
        @NotNull(message = "firstname required")
        String firstname,
        String lastname,
        @NotNull(message = "date of birth required!")
        Date dateOfBirth,
        String birthPlace,
        @NotNull(message = "email required!")
        String email,
        @NotNull(message = "phone number required!")
        String phone,
        @NotNull(message = "address required!")
        String address
) {
}
