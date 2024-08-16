package com.fuji.professor_service.controller;

import com.fuji.professor_service.DTO.ProfessorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface FileController {
    @PatchMapping("add-image-profile")
    ResponseEntity<ProfessorResponse> uploadProfilePhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") String id
    );
}
