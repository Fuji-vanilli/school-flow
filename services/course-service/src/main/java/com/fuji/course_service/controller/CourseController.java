package com.fuji.course_service.controller;

import com.fuji.course_service.DTO.CourseRequest;
import com.fuji.course_service.DTO.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CourseController {
    @PostMapping("create")
    ResponseEntity<CourseResponse> create(@RequestBody CourseRequest request);
    @PutMapping("update")
    ResponseEntity<CourseResponse> update(@RequestBody CourseRequest request);
    @GetMapping("get/{code}")
    ResponseEntity<CourseResponse> get(@PathVariable String code);
    @PostMapping("all-by-ids")
    ResponseEntity<List<CourseResponse>> allByIDs(@RequestBody List<String> ids);
    @GetMapping("all")
    ResponseEntity<List<CourseResponse>> getAll();
    @DeleteMapping("delete/{code}")
    ResponseEntity<CourseResponse> delete(@PathVariable String code);
}
