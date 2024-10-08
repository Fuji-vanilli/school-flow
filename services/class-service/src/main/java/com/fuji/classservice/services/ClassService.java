package com.fuji.classservice.services;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.models.Professor;

import java.util.List;
import java.util.Map;

public interface ClassService {
    ClassResponse create(ClassRequest request);
    ClassResponse update(ClassRequest request);
    ClassResponse addStudent(Map<String, String> params);
    ClassResponse addCourse(Map<String, String> params);
    ClassResponse addProfessor(Map<String, String> params);
    ClassResponse getByLevel(String level);
    ClassResponse getById(String id);
    ClassResponse deleteStudentFromClass(Map<String, String> query);
    List<ClassResponse> getAll();
    List<ClassResponse> getAllByIds(List<String> ids);
    ClassResponse delete(String id);
}
