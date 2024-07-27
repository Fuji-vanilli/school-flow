package com.fuji.professor_service.DTO;

import java.util.Date;

public record ProfessorRequest(
        String id,
        String matricule,
        String firstname,
        String lastname,
        Date dateOfBirth,
        String birthPlace,
        String email,
        String phone,
        String address
) {
}
