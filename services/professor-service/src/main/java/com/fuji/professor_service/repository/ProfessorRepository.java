package com.fuji.professor_service.repository;

import com.fuji.professor_service.entities.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfessorRepository extends MongoRepository<Professor,String> {
    Optional<Professor> findByMatricule(String matricule);
}
