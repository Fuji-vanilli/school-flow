package com.fuji.professor_service.mapper;

import com.fuji.professor_service.DTO.ProfessorRequest;
import com.fuji.professor_service.DTO.ProfessorResponse;
import com.fuji.professor_service.entities.Professor;

public interface ProfessorMapper {
    Professor mapToProfessor(ProfessorRequest request);
    ProfessorResponse mapToProfessorResponse(Professor professor);
}
