package com.fuji.student_service.services;

import com.fuji.student_service.DTO.StudentRequest;
import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.entities.Student;
import com.fuji.student_service.mapper.StudentMapper;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;
import com.fuji.student_service.models.Report;
import com.fuji.student_service.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = studentMapper.mapToStudent(request);
        Random random= new Random();

        String matricule= request.aClass().level().substring(0, 2)+"-"+ random.nextInt(100);
        student.setMatricule(matricule);
        student.setId(UUID.randomUUID().toString());
        student.setCreatedDate(Instant.now());
        student.setLastUpdatedDate(Instant.now());

        studentRepository.save(student);
        log.info("Student created: {}", student);

        return studentMapper.mapToStudentResponse(student);
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
    public List<StudentResponse> getAll() {
        log.info("all students getted successfully!");
        return studentRepository.findAll().stream()
                .map(studentMapper::mapToStudentResponse)
                .toList();
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
