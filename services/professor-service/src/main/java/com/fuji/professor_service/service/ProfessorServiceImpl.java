package com.fuji.professor_service.service;

import com.fuji.professor_service.DTO.ProfessorRequest;
import com.fuji.professor_service.DTO.ProfessorResponse;
import com.fuji.professor_service.ProfessorRepository;
import com.fuji.professor_service.mapper.ProfessorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfessorServiceImpl implements ProfessorService{
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    @Override
    public ProfessorResponse create(ProfessorRequest request) {
        return null;
    }

    @Override
    public ProfessorResponse update(ProfessorRequest request) {
        return null;
    }

    @Override
    public ProfessorResponse getByMatricule(String matricule) {
        return null;
    }

    @Override
    public ProfessorResponse getAll() {
        return null;
    }

    @Override
    public ProfessorResponse addCourse(Map<String, String> params) {
        return null;
    }

    @Override
    public ProfessorResponse addCourseApply(Map<String, String> params) {
        return null;
    }

    @Override
    public ProfessorResponse delete(String matricule) {
        return null;
    }
}
