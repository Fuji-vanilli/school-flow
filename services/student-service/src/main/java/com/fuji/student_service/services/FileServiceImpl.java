package com.fuji.student_service.services;

import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.entities.Student;
import com.fuji.student_service.mapper.StudentMapper;
import com.fuji.student_service.repository.StudentRepository;
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
public class FileServiceImpl implements FileService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Value("${image.storage.local.directory}")
    private String localDirectory;

    @Override
    public StudentResponse uploadProfileImage(MultipartFile file, String id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("student with the id: " + id + " doesn't exist!")
        );

        String originalFilename = file.getOriginalFilename();

        String filenameExtension = StringUtils.getFilenameExtension(originalFilename);

        String newFileName= "IMG_"+ UUID.randomUUID().toString()+"."+filenameExtension;
        Path pathFile= Paths.get(localDirectory, newFileName);

        String pathLocation= localDirectory+"\\"+newFileName;
        student.setImageUrl("../../../../../assets/"+newFileName);

        try {
            file.transferTo(pathFile.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        studentRepository.save(student);
        log.info("image uploaded successfully!");
        return studentMapper.mapToStudentResponse(student);
    }
}
