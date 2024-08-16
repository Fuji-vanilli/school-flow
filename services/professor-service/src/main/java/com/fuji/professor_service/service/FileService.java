package com.fuji.professor_service.service;

import com.fuji.professor_service.DTO.ProfessorResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    ProfessorResponse uploadProfileImage(MultipartFile file, String id);
}