package com.fuji.student_service.DTO;

import com.fuji.student_service.entities.Address;
import com.fuji.student_service.models.Class;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public record StudentRequest (
        String id,
        @NotNull(message = "firstname required!")
        String firstname,
        @NotNull(message = "lastname required!")
        String lastname,
        @NotNull(message = "date of birth required!")
        Date dateOfBirth,
        @NotNull(message = "birth place required!")
        String birthPlace,
        String email,
        @NotNull(message = "phone number required!")
        String phone,
        @NotNull(message = "address required!")
        String address,
        @NotNull(message = "class id required!")
        String classID,
        String originSchool
){
}
