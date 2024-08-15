package com.fuji.classservice.services;

import com.fuji.classservice.DTO.ClassRequest;
import com.fuji.classservice.DTO.ClassResponse;
import com.fuji.classservice.entities.Class;
import com.fuji.classservice.mapper.ClassMapper;
import com.fuji.classservice.models.Professor;
import com.fuji.classservice.models.Student;
import com.fuji.classservice.repository.ClassRepository;
import com.fuji.classservice.webClient.WebClientCourse;
import com.fuji.classservice.webClient.WebClientStudentGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService{
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;
    private final WebClientStudentGetter webClientStudent;
    private final WebClientCourse webClientCourse;

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
        Optional<Class> classById = classRepository.findById(request.id());
        if (classById.isEmpty()) {
            log.error("sorry, class {} does not exist into the database", request.id());
            throw new IllegalArgumentException("sorry, class does not exist into the database");
        }

        Class aClass = classById.get();
        migrateClass(request, aClass);

        aClass.setLastModifiedDate(Instant.now());
        classRepository.save(aClass);

        log.info("class updated successfully");
        return classMapper.mapToClassResponse(aClass);
    }

    private void migrateClass(ClassRequest request, Class aClass) {
        if (!request.level().isBlank()) {
            aClass.setLevel(request.level());
        }
        if (!Objects.isNull(request.section())) {
            aClass.setSection(request.section());
        }
        if (!Objects.isNull(request.ecolage())) {
            aClass.setEcolage(request.ecolage());
        }
        if (!Objects.isNull(request.maximumCapacity())) {
            aClass.setMaximumCapacity(request.maximumCapacity());
        }
    }

    @Override
    public ClassResponse addStudent(Map<String, String> params) {
        final String studentID= params.get("studentID");
        final String classID= params.get("classID");

        Optional<Class> classOptional = classRepository.findById(classID);
        if (classOptional.isEmpty()) {
            log.error("sorry, class {} does not exist into the database", studentID);
            throw new IllegalArgumentException("sorry, class does not exist into the database");
        }

        Class aClass = classOptional.get();
        aClass.getStudentsID().add(studentID);
        aClass.setLastModifiedDate(Instant.now());

        classRepository.save(aClass);
        log.info("student {} added successfully to the class {}", studentID, classID);

        return classMapper.mapToClassResponse(aClass);
    }

    @Override
    public ClassResponse addCourse(Map<String, String> params) {
        final String courseID= params.get("courseID");
        final String classID= params.get("classID");

        Optional<Class> classOptional = classRepository.findById(classID);
        if (classOptional.isEmpty()) {
            log.error("sorry, class {} does not exist into the database", courseID);
            throw new IllegalArgumentException("sorry, course does not exist into the database");
        }

        Class aClass = classOptional.get();
        aClass.getCoursesID().add(courseID);
        aClass.setLastModifiedDate(Instant.now());

        classRepository.save(aClass);
        log.info("Course {} added successfully to the class {}", courseID, classID);

        return classMapper.mapToClassResponse(aClass);
    }

    @Override
    public ClassResponse addProfessor(Professor professor) {
        return null;
    }

    @Override
    public ClassResponse getByLevel(String level) {
        return null;
    }

    @Override
    public ClassResponse getById(String id) {
        Optional<Class> classOptional = classRepository.findById(id);
        if (classOptional.isEmpty()) {
            log.error("sorry, class {} does not exist into the database", id);
            throw new IllegalArgumentException("sorry, class does not exist into the database");
        }

        Class aClass = classOptional.get();
        List<String> studentsID = aClass.getStudentsID();

        List<Student> allStudentsByIds = webClientStudent.getAllStudentsByIds(studentsID);
        aClass.setStudents(allStudentsByIds);

        aClass.setStudents(null);
        log.info("class {} getted successfully", aClass.getId());

        return classMapper.mapToClassResponse(aClass);
    }

    @Override
    public ClassResponse deleteStudentFromClass(Map<String, String> query) {
        final String classID= query.get("classID");
        final String studentID= query.get("studentID");

        Optional<Class> classByID = classRepository.findById(classID);
        if (classByID.isEmpty()) {
            log.error("sorry, class {} does not exist into the database", classID);
            throw new IllegalArgumentException("sorry class: "+classID+" does not exist into the database");
        }

        Class aClass = classByID.get();
        aClass.getStudentsID().remove(studentID);
        aClass.setLastModifiedDate(Instant.now());

        classRepository.save(aClass);
        log.info("student with the class {} deleted successfully", aClass.getId());

        return classMapper.mapToClassResponse(aClass);
    }

    @Override
    public List<ClassResponse> getAll() {
        log.info("all class getted successfully");
        return classRepository.findAll().stream()
                .map(aClass-> {
                    final List<String> studentsIDs= aClass.getStudentsID();
                    final List<String> courseIDs= aClass.getCoursesID();

                    aClass.setStudents(webClientStudent.getAllStudentsByIds(studentsIDs));
                    aClass.setCourses(webClientCourse.getAllCourseByIDs(courseIDs));

                    return classMapper.mapToClassResponse(aClass); 
                })
                .toList();
    }

    @Override
    public List<ClassResponse> getAllByIds(List<String> ids) {
        log.info("all class by ids getted successfully!");
        return classRepository.findAllById(ids).stream()
                .map(classMapper::mapToClassResponse)
                .toList();
    }

    @Override
    public ClassResponse delete(String id) {
        Optional<Class> classById = classRepository.findById(id);
        if (classById.isEmpty()) {
            log.error("sorry, class does not exist into the database");
            throw new IllegalArgumentException("sorry, class does not exist into the database");
        }

        classRepository.deleteById(id);
        log.info("class deleted successfully");

        return classMapper.mapToClassResponse(classById.get());
    }
}
