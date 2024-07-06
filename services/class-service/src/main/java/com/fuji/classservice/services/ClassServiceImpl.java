package com.fuji.classservice.services;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.entities.Class;
import com.fuji.classservice.entities.Level;
import com.fuji.classservice.mapper.ClassMapper;
import com.fuji.classservice.models.Professor.Professor;
import com.fuji.classservice.models.Student.Student;
import com.fuji.classservice.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService{
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;

    @Override
    public ClassResponse create(ClassRequest request) {
        if (classRepository.existsByLevel(request.level())) {
            log.error("sorry, class already exist into the database");
            throw new IllegalArgumentException("sorry, class already exist into the database");
        }

        Class aClass = classMapper.mapToClass(request);
        aClass.setId(UUID.randomUUID().toString());
        aClass.setCreatedDate(Instant.now());
        aClass.setLastModifiedDate(Instant.now());

        classRepository.save(aClass);
        log.info("new class created successfully");

        return classMapper.mapToClassResponse(aClass);
    }

    @Override
    public ClassResponse update(ClassRequest request) {
        return null;
    }

    @Override
    public ClassResponse addStudent(Student student) {
        return null;
    }

    @Override
    public ClassResponse addProfessor(Professor professor) {
        return null;
    }

    @Override
    public ClassResponse getByLevel(Level level) {
        return null;
    }

    @Override
    public List<ClassResponse> getAll() {
        return List.of();
    }

    @Override
    public ClassResponse delete(String id) {
        return null;
    }
}
