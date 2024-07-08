package com.fuji.classservice.controller;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.models.Professor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClassController {
    @PostMapping("create")
    ResponseEntity<ClassResponse> create(@RequestBody ClassRequest request);
    @PutMapping("update")
    ResponseEntity<ClassResponse> update(@RequestBody ClassRequest request);
    @PatchMapping("add-student/{id}")
    ResponseEntity<ClassResponse> addStudent(@PathVariable String id);
    @PatchMapping("add-professor")
    ResponseEntity<ClassResponse> addProfessor(@RequestBody Professor professor);
    @GetMapping("get/{level}")
    ResponseEntity<ClassResponse> get(@PathVariable String level);
    @GetMapping("all")
    ResponseEntity<List<ClassResponse>> getAll();
    @DeleteMapping("delete/{id}")
    ResponseEntity<ClassResponse> delete(@PathVariable String id);
}
