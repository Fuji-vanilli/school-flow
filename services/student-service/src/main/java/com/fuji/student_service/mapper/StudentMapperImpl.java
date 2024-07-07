package com.fuji.student_service.mapper;

import com.fuji.student_service.DTO.StudentRequest;
import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.entities.Student;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentMapperImpl implements StudentMapper{
    @Override
    public Student mapToStudent(StudentRequest request) {
        return Student.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .phone(request.phone())
                .email(request.email())
                .address(request.address())
                .aClass(request.aClass())
                .dateOfBirth(request.dateOfBirth())
                .birthPlace(request.birthPlace())
                .originSchool(request.originSchool())
                .build();
    }

    @Override
    public StudentResponse mapToStudentResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getMatricule(),
                student.getFirstname(),
                student.getLastname(),
                student.getDateOfBirth(),
                student.getBirthPlace(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress(),
                student.getImageUrl(),
                student.getAClass(),
                student.getEcolages(),
                student.getNote(),
                student.getReport(),
                student.getOriginSchool(),
                student.getCreatedDate(),
                student.getLastUpdatedDate()
        );
    }
}
