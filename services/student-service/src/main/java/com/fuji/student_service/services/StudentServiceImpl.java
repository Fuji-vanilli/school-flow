package com.fuji.student_service.services;

import com.fuji.student_service.DTO.StudentRequest;
import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.mapper.StudentMapper;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;
import com.fuji.student_service.models.Report;
import com.fuji.student_service.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse create(StudentRequest request) {
        return null;
    }

    @Override
    public StudentResponse update(StudentRequest request) {
        return null;
    }

    @Override
    public StudentResponse getByMatricule(String matricule) {
        return null;
    }

    @Override
    public StudentResponse addNotes(Note note) {
        return null;
    }

    @Override
    public StudentResponse paymentEcolage(Ecolage ecolage) {
        return null;
    }

    @Override
    public StudentResponse addReport(Report report) {
        return null;
    }

    @Override
    public StudentResponse delete(String matricule) {
        return null;
    }
}
