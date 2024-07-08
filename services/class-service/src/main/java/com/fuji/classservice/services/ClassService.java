package com.fuji.classservice.services;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.models.Professor;

import java.util.List;

public interface ClassService {
    ClassResponse create(ClassRequest request);
    ClassResponse update(ClassRequest request);
    ClassResponse addStudent(String id);
    ClassResponse addProfessor(Professor professor);
    ClassResponse getByLevel(String level);
    List<ClassResponse> getAll();
    ClassResponse delete(String id);
}
