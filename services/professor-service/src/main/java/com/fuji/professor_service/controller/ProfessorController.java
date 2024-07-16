package com.fuji.professor_service.controller;

import com.fuji.professor_service.DTO.ProfessorRequest;
import com.fuji.professor_service.DTO.ProfessorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface ProfessorController {
    @PostMapping("create")
    ResponseEntity<ProfessorResponse> create(@RequestBody ProfessorRequest request);
    @PutMapping("update")
    ResponseEntity<ProfessorResponse> update(@RequestBody ProfessorRequest request);
    @GetMapping("get/{matricule}")
    ResponseEntity<ProfessorResponse> get(@PathVariable String matricule);
    @GetMapping("all")
    ResponseEntity<ProfessorResponse> getAll();
    @PatchMapping("add-course")
    ResponseEntity<ProfessorResponse> addCourse(@RequestBody Map<String, String> params);
    @PatchMapping("add-course-apply")
    ResponseEntity<ProfessorResponse> addCourseApply(@RequestBody Map<String, String> params);
    @DeleteMapping("delete/{matricule}")
    ResponseEntity<ProfessorResponse> delete(@PathVariable String matricule);
}
