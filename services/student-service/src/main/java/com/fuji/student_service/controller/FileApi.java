package com.fuji.student_service.controller;

import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.fuji.student_service.utils.Root.APP_ROOT;


@RestController
@RequiredArgsConstructor
@RequestMapping(APP_ROOT)
public class FileApi implements FileController {
    private final FileService fileService;

    @Override
    public ResponseEntity<StudentResponse> uploadProfilePhoto(MultipartFile file, String id) {
        return ResponseEntity.ok(fileService.uploadProfileImage(file, id));
    }
}
