package com.fuji.course_service.controller;

import com.fuji.course_service.DTO.CourseRequest;
import com.fuji.course_service.DTO.CourseResponse;
import com.fuji.course_service.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fuji.course_service.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class CourseApi implements CourseController{
    private final CourseService courseService;

    @Override
    public ResponseEntity<CourseResponse> create(CourseRequest request) {
        return ResponseEntity.ok(courseService.create(request));
    }

    @Override
    public ResponseEntity<CourseResponse> update(CourseRequest request) {
        return ResponseEntity.ok(courseService.update(request));
    }

    @Override
    public ResponseEntity<CourseResponse> get(String code) {
        return ResponseEntity.ok(courseService.getByCode(code));
    }

    @Override
    public ResponseEntity<List<CourseResponse>> allByIDs(List<String> ids) {
        return ResponseEntity.ok(courseService.getAllByIDs(ids));
    }

    @Override
    public ResponseEntity<List<CourseResponse>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @Override
    public ResponseEntity<CourseResponse> delete(String code) {
        return ResponseEntity.ok(courseService.delete(code));
    }
}
