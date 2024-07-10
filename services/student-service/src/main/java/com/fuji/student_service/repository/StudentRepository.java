package com.fuji.student_service.repository;

import com.fuji.student_service.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student,String> {
    boolean existsByMatricule(String matricule);
    Optional<Student> findByMatricule(String matricule);
    void deleteByMatricule(String matricule);
    List<Student> findAllByClassID(String classID);
}
