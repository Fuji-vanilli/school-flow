package com.fuji.professor_service.DTO;

import com.fuji.professor_service.models.Class;
import com.fuji.professor_service.models.Course;
import com.fuji.professor_service.models.CourseApply;
import org.springframework.data.annotation.Transient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record ProfessorResponse (
        String id,
        String matricule,
        String firstname,
        String lastname,
        Date dateOfBirth,
        String birthPlace,
        String email,
        String phone,
        String address,
        String degree,
        List<String> classIDs,
        List<CourseApply> courseApplies,
        Instant createdDate,
        Instant lastUpdatedDate,
        List<Class> classes,
        List<Course> courses
){
}
