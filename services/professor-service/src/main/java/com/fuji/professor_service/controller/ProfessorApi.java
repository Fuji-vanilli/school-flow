package com.fuji.professor_service.controller;

import com.fuji.professor_service.DTO.ProfessorRequest;
import com.fuji.professor_service.DTO.ProfessorResponse;
import com.fuji.professor_service.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.fuji.professor_service.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class ProfessorApi implements ProfessorController{
    private final ProfessorService professorService;

    @Override
    public ResponseEntity<ProfessorResponse> create(ProfessorRequest request) {
        return ResponseEntity.ok(professorService.create(request));
    }

    @Override
    public ResponseEntity<ProfessorResponse> update(ProfessorRequest request) {
        return ResponseEntity.ok(professorService.update(request));
    }

    @Override
    public ResponseEntity<ProfessorResponse> getByID(String id) {
        return ResponseEntity.ok(professorService.getById(id));
    }

    @Override
    public ResponseEntity<ProfessorResponse> getByMatricule(String matricule) {
        return ResponseEntity.ok(professorService.getByMatricule(matricule));
    }

    @Override
    public ResponseEntity<List<ProfessorResponse>> getAll() {
        return ResponseEntity.ok(professorService.getAll());
    }

    @Override
    public ResponseEntity<ProfessorResponse> addCourse(Map<String, String> params) {
        return ResponseEntity.ok(professorService.addCourse(params));
    }

    @Override
    public ResponseEntity<ProfessorResponse> addCourseApply(Map<String, String> params) {
        return ResponseEntity.ok(professorService.addCourseApply(params));
    }

    @Override
    public ResponseEntity<ProfessorResponse> delete(String matricule) {
        return ResponseEntity.ok(professorService.delete(matricule));
    }
}
