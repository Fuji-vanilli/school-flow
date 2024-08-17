package com.fuji.student_service.services;

import com.fuji.student_service.DTO.StudentResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    StudentResponse uploadProfileImage(MultipartFile file, String id);
}