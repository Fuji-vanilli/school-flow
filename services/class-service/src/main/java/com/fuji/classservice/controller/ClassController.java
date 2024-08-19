package com.fuji.classservice.controller;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.models.Professor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface ClassController {
    @PostMapping("create")
    ResponseEntity<ClassResponse> create(@RequestBody ClassRequest request);
    @PutMapping("update")
    ResponseEntity<ClassResponse> update(@RequestBody ClassRequest request);
    @PatchMapping("add-student")
    ResponseEntity<ClassResponse> addStudent(@RequestBody Map<String, String> params);
    @PatchMapping("add-course")
    ResponseEntity<ClassResponse> addCourse(@RequestBody Map<String, String> params);
    @PatchMapping("add-professor")
    ResponseEntity<ClassResponse> addProfessor(@RequestBody Map<String, String> params);
    @GetMapping("get-by-id/{id}")
    ResponseEntity<ClassResponse> getByID(@PathVariable String id);
    @GetMapping("get-by-level/{level}")
    ResponseEntity<ClassResponse> get(@PathVariable String level);
    @GetMapping("all")
    ResponseEntity<List<ClassResponse>> getAll();
    @PostMapping("get-all-by-ids")
    ResponseEntity<List<ClassResponse>> allByIds(@RequestBody List<String> ids);
    @PatchMapping("delete-student")
    ResponseEntity<ClassResponse> deleteStudent(@RequestBody Map<String, String> params);
    @DeleteMapping("delete/{id}")
    ResponseEntity<ClassResponse> delete(@PathVariable String id);
}
