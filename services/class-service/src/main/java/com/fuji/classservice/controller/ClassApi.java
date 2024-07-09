package com.fuji.classservice.controller;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.models.Professor;
import com.fuji.classservice.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.fuji.classservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class ClassApi implements ClassController {
    private final ClassService classService;

    @Override
    public ResponseEntity<ClassResponse> create(ClassRequest request) {
        return ResponseEntity.ok(classService.create(request));
    }

    @Override
    public ResponseEntity<ClassResponse> update(ClassRequest request) {
        return ResponseEntity.ok(classService.update(request));
    }

    @Override
    public ResponseEntity<ClassResponse> addStudent(Map<String, String> params) {
        return ResponseEntity.ok(classService.addStudent(params));
    }

    @Override
    public ResponseEntity<ClassResponse> getByID(String id) {
        return ResponseEntity.ok(classService.getById(id));
    }

    @Override
    public ResponseEntity<ClassResponse> addProfessor(Professor professor) {
        return ResponseEntity.ok(classService.addProfessor(professor));
    }

    @Override
    public ResponseEntity<ClassResponse> get(String level) {
        return ResponseEntity.ok(classService.getByLevel(level));
    }

    @Override
    public ResponseEntity<List<ClassResponse>> getAll() {
        return ResponseEntity.ok(classService.getAll());
    }

    @Override
    public ResponseEntity<ClassResponse> delete(String id) {
        return ResponseEntity.ok(classService.delete(id));
    }
}
