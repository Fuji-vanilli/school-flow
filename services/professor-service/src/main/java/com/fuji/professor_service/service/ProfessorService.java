package com.fuji.professor_service.service;

import com.fuji.professor_service.DTO.ProfessorRequest;
import com.fuji.professor_service.DTO.ProfessorResponse;

import java.util.Map;

public interface ProfessorService {
    ProfessorResponse create(ProfessorRequest request);
    ProfessorResponse update(ProfessorRequest request);
    ProfessorResponse getByMatricule(String matricule);
    ProfessorResponse getAll();
    ProfessorResponse addCourse(Map<String, String> params);
    ProfessorResponse addCourseApply(Map<String, String> params);
    ProfessorResponse delete(String matricule);
}
