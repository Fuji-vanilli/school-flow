package com.fuji.student_service.services;

import com.fuji.student_service.DTO.StudentRequest;
import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.entities.Student;
import com.fuji.student_service.mapper.StudentMapper;
import com.fuji.student_service.models.Class;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;
import com.fuji.student_service.models.Report;
import com.fuji.student_service.repository.StudentRepository;
import com.fuji.student_service.webClient.WebClientClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final WebClientClass webClientClass;

    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = studentMapper.mapToStudent(request);
        Random random= new Random();

        Class classByID = webClientClass.getClassByID(student.getClassID());

        String matricule= classByID.level().substring(0, 2)+"-"+ random.nextInt(100);
        student.setMatricule(matricule);
        student.setId(UUID.randomUUID().toString());
        student.setCreatedDate(Instant.now());
        student.setLastUpdatedDate(Instant.now());
        student.setAClass(classByID);

        studentRepository.save(student);
        log.info("Student created: {}", student);

        return studentMapper.mapToStudentResponse(student);
    }

    @Override
    public StudentResponse update(StudentRequest request) {
        Optional<Student> studentByID = studentRepository.findById(request.id());
        if (studentByID.isEmpty()) {
            log.error("Student with id {} not found", request.id());
            throw new IllegalArgumentException("Student with id " + request.id() + " not found");
        }

        Student student = studentByID.get();
        mergeStudent(request, student);

        student.setLastUpdatedDate(Instant.now());

        studentRepository.save(student);
        log.info("Student updated: {}", student);

        return studentMapper.mapToStudentResponse(student);
    }

    @Override
    public StudentResponse get(String id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            log.error("Student with id : {} not found", id);
            throw new IllegalArgumentException("Student with id " + id + " not found");
        }
        Student student = studentOptional.get();

        Class classByID = webClientClass.getClassByID(student.getClassID());
        student.setAClass(classByID);

        webClientClass.getClassByID(student.getClassID());
        student.setAClass(student.getAClass());

        log.info("Student getted successfully");
        return studentMapper.mapToStudentResponse(student);
    }

    public void mergeStudent(StudentRequest request, Student student) {
        if (!request.firstname().isBlank()) {
            student.setFirstname(request.firstname());
        }
        if (!request.lastname().isBlank()) {
            student.setLastname(request.lastname());
        }
        if (!request.email().isBlank()) {
            student.setEmail(request.email());
        }
        if (!request.phone().isBlank()) {
            student.setPhone(request.phone());
        }
        if (!Objects.isNull(request.dateOfBirth())) {
            student.setDateOfBirth(request.dateOfBirth());
        }
        if (!request.birthPlace().isBlank()) {
            student.setBirthPlace(request.birthPlace());
        }
        if (!request.originSchool().isBlank()) {
            student.setOriginSchool(request.originSchool());
        }
        if (!request.address().isBlank()) {
            student.setAddress(request.address());
        }
        if (request.classID().isBlank()) {
            student.setClassID(request.classID());
        }
    }

    @Override
    public StudentResponse getByMatricule(String matricule) {
        Optional<Student> studentOptional = studentRepository.findByMatricule(matricule);
        if (studentOptional.isEmpty()) {
            log.error("Student with matricule {} not found", matricule);
            throw new IllegalArgumentException("Student with id " + matricule + " not found");
        }

        Student student = studentOptional.get();
        Class classByID = webClientClass.getClassByID(student.getClassID());

        student.setAClass(classByID);
        log.info("Student getted successfully: {}", student);
        return studentMapper.mapToStudentResponse(student);
    }

    @Override
    public List<StudentResponse> getAll() {
        log.info("all students getted successfully!");
        return studentRepository.findAll().stream()
                .map(student-> {
                    Class classByID = webClientClass.getClassByID(student.getClassID());
                    student.setAClass(classByID);

                    return studentMapper.mapToStudentResponse(student);
                })
                .toList();
    }

    @Override
    public List<StudentResponse> getAllByIds(List<String> ids) {
        log.info("all students with the ids : {}getted successfully", ids);
        return studentRepository.findAllById(ids).stream()
                .map(studentMapper::mapToStudentResponse)
                .toList();
    }

    @Override
    public List<StudentResponse> getAllByClassID(String classID) {
        Class classByID = webClientClass.getClassByID(classID);
        log.info("all students with the classID : {}getted successfully", classID);

        return studentRepository.findAllByClassID(classID).stream()
                .map(student -> {
                    student.setAClass(classByID);
                    return studentMapper.mapToStudentResponse(student);
                })
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
        Optional<Student> studentByMatricule = studentRepository.findByMatricule(matricule);
        if (studentByMatricule.isEmpty()) {
            log.error("Student with id {} doesn't found", matricule);
            throw new IllegalArgumentException("Student with id " + matricule + " not found");
        }

        Student student = studentByMatricule.get();

        Map<String, String> params = Map.of("classID", student.getAClass().id(), "studentID", student.getId());
        webClientClass.deleteStudentFromClass(params);

        studentRepository.deleteByMatricule(matricule);
        log.info("Student deleted: {}", studentByMatricule.get());

        return studentMapper.mapToStudentResponse(student);
    }
}
