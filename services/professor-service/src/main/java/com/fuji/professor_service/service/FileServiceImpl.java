package com.fuji.professor_service.service;

import com.fuji.professor_service.DTO.ProfessorResponse;
import com.fuji.professor_service.entities.Professor;
import com.fuji.professor_service.mapper.ProfessorMapper;
import com.fuji.professor_service.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService{
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    @Value("${image.storage.local.directory}")
    private String localDirectory;

    @Override
    public ProfessorResponse uploadProfileImage(MultipartFile file, String id) {
        Professor professor = professorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("professor with the id: " + id + " doesn't exist!")
        );

        String originalFilename = file.getOriginalFilename();

        String filenameExtension = StringUtils.getFilenameExtension(originalFilename);

        String newFileName= "IMG_"+ UUID.randomUUID().toString()+"."+filenameExtension;
        Path pathFile= Paths.get(localDirectory, newFileName);

        String pathLocation= localDirectory+"\\"+newFileName;
        professor.setImageUrl("../../../../../assets/"+newFileName);

        try {
            file.transferTo(pathFile.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        professorRepository.save(professor);
        log.info("image uploaded successfully!");
        return professorMapper.mapToProfessorResponse(professor);
    }
}
