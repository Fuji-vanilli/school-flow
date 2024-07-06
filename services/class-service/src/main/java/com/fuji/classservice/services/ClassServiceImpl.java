package com.fuji.classservice.services;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
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

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService{
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;

    @Override
    public ClassResponse create(ClassRequest request) {
        return null;
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
