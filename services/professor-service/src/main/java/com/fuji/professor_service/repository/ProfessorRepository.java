package com.fuji.professor_service.repository;

import com.fuji.professor_service.entities.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorRepository extends MongoRepository<Professor,String> {

}
