package com.fuji.student_service.mapper;

import com.fuji.student_service.DTO.StudentRequest;
import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.entities.Student;

public interface StudentMapper {
    Student mapToStudent(StudentRequest request);
    StudentResponse mapToStudentResponse(Student student);
}
