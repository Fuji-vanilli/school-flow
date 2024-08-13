package com.fuji.professor_service.service;

import com.fuji.professor_service.DTO.ProfessorRequest;
import com.fuji.professor_service.DTO.ProfessorResponse;
import com.fuji.professor_service.ProfessorRepository;
import com.fuji.professor_service.entities.Professor;
import com.fuji.professor_service.mapper.ProfessorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfessorServiceImpl implements ProfessorService{
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    @Override
    public ProfessorResponse create(ProfessorRequest request) {
        Professor professor= professorMapper.mapToProfessor(request);
        Random random= new Random();

        professor.setId(UUID.randomUUID().toString());
        professor.setMatricule("PR_"+random.nextInt(1000));

        professor.setCreatedDate(Instant.now());
        professor.setLastUpdatedDate(Instant.now());

        professorRepository.save(professor);
        log.info("new professor created successfully!");
        return professorMapper.mapToProfessorResponse(professor);
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
    public List<ProfessorResponse> getAll() {

        log.info("All professors getted successfully");
        return professorRepository.findAll().stream()
                .map(professorMapper::mapToProfessorResponse)
                .toList();
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
