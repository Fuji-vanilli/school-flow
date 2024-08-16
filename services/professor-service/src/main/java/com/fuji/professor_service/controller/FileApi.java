package com.fuji.professor_service.controller;

import com.fuji.professor_service.DTO.ProfessorResponse;
import com.fuji.professor_service.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.fuji.professor_service.utils.Root.APP_ROOT;

@RestController
@RequiredArgsConstructor
@RequestMapping(APP_ROOT)
public class FileApi implements FileController{
    private final FileService fileService;

    @Override
    public ResponseEntity<ProfessorResponse> uploadProfilePhoto(MultipartFile file, String id) {
        return ResponseEntity.ok(fileService.uploadProfileImage(file, id));
    }
}
