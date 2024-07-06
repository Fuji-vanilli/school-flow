package com.fuji.classservice.services;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.entities.Level;
import com.fuji.classservice.models.Professor.Professor;
import com.fuji.classservice.models.Student.Student;

import java.util.List;

public interface ClassService {
    ClassResponse create(ClassRequest request);
    ClassResponse update(ClassRequest request);
    ClassResponse addStudent(Student student);
    ClassResponse addProfessor(Professor professor);
    ClassResponse getByLevel(Level level);
    List<ClassResponse> getAll();
    ClassResponse delete(String id);
}
