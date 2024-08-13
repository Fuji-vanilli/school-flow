package com.fuji.professor_service.service;

import com.fuji.professor_service.DTO.ProfessorRequest;
import com.fuji.professor_service.DTO.ProfessorResponse;
import com.fuji.professor_service.repository.ProfessorRepository;
import com.fuji.professor_service.entities.Professor;
import com.fuji.professor_service.mapper.ProfessorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

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
        Optional<Professor> professorByID = professorRepository.findById(request.id());
        if (professorByID.isEmpty()) {
            log.error("Professor with id {} not found", request.id());
            throw new IllegalArgumentException("Professor with id " + request.id() + " not found");
        }

        Professor professor = professorByID.get();
        mergeProfessor(request, professor);

        professor.setLastUpdatedDate(Instant.now());

        professorRepository.save(professor);
        log.info("Student updated: {}", professor);

        return professorMapper.mapToProfessorResponse(professor);
    }


    public void mergeProfessor(ProfessorRequest request, Professor student) {
        if (!request.firstname().isBlank()) {
            student.setFirstname(request.firstname());
        }
        if (!request.lastname().isBlank()) {
            student.setLastname(request.lastname());
        }
        if (!request.email().isBlank()) {
            student.setEmail(request.email());
        }
        if (!request.phone().isBlank()) {
            student.setPhone(request.phone());
        }
        if (!Objects.isNull(request.dateOfBirth())) {
            student.setDateOfBirth(request.dateOfBirth());
        }
        if (!request.birthPlace().isBlank()) {
            student.setBirthPlace(request.birthPlace());
        }
        if (!request.address().isBlank()) {
            student.setAddress(request.address());
        }
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
