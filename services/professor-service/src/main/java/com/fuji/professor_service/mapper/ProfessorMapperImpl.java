package com.fuji.professor_service.mapper;

import com.fuji.professor_service.DTO.ProfessorRequest;
import com.fuji.professor_service.DTO.ProfessorResponse;
import com.fuji.professor_service.entities.Professor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfessorMapperImpl implements ProfessorMapper{
    @Override
    public Professor mapToProfessor(ProfessorRequest request) {
        return Professor.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .dateOfBirth(request.dateOfBirth())
                .birthPlace(request.birthPlace())
                .build();
    }

    @Override
    public ProfessorResponse mapToProfessorResponse(Professor professor) {
        return new ProfessorResponse(
                professor.getId(),
                professor.getMatricule(),
                professor.getFirstname(),
                professor.getLastname(),
                professor.getDateOfBirth(),
                professor.getBirthPlace(),
                professor.getEmail(),
                professor.getAddress(),
                professor.getDegree(),
                professor.getImageUrl(),
                professor.getClassIDs(),
                professor.getCourseApplies(),
                professor.getCreatedDate(),
                professor.getLastUpdatedDate(),
                professor.getClasses(),
                professor.getCourses()
        );
    }
}
