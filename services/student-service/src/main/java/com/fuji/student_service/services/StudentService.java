package com.fuji.student_service.services;

import com.fuji.student_service.DTO.StudentRequest;
import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;
import com.fuji.student_service.models.Report;

import java.util.List;
import java.util.Map;

public interface StudentService {
    StudentResponse create(StudentRequest request);
    StudentResponse update(StudentRequest request);
    StudentResponse get(String id);
    StudentResponse getByMatricule(String matricule);
    List<StudentResponse> getAll();
    List<StudentResponse> getAllByIds(List<String> ids);
    StudentResponse addNotes(Note note);
    StudentResponse paymentEcolage(Ecolage ecolage);
    StudentResponse addReport(Report report);
    StudentResponse delete(String matricule);
}
