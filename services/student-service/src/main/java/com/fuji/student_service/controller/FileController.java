package com.fuji.student_service.controller;

import com.fuji.student_service.DTO.StudentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface FileController {
    @PatchMapping("add-image-profile")
    ResponseEntity<StudentResponse> uploadProfilePhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") String id
    );
}
