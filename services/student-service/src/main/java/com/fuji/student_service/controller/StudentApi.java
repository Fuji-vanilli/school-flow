package com.fuji.student_service.controller;

import com.fuji.student_service.DTO.StudentRequest;
import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;
import com.fuji.student_service.models.Report;
import com.fuji.student_service.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fuji.student_service.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class StudentApi implements StudentController{
    private final StudentService studentService;
    @Override
    public ResponseEntity<StudentResponse> create(StudentRequest request) {
        return ResponseEntity.ok(studentService.create(request));
    }

    @Override
    public ResponseEntity<StudentResponse> update(StudentRequest request) {
        return ResponseEntity.ok(studentService.update(request));
    }

    @Override
    public ResponseEntity<StudentResponse> getByMatricule(String matricule) {
        return ResponseEntity.ok(studentService.getByMatricule(matricule));
    }

    @Override
    public ResponseEntity<List<StudentResponse>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @Override
    public ResponseEntity<StudentResponse> addNotes(Note note) {
        return ResponseEntity.ok(studentService.addNotes(note));
    }

    @Override
    public ResponseEntity<StudentResponse> paymentEcolage(Ecolage ecolage) {
        return ResponseEntity.ok(studentService.paymentEcolage(ecolage));
    }

    @Override
    public ResponseEntity<StudentResponse> addReport(Report report) {
        return ResponseEntity.ok(studentService.addReport(report));
    }

    @Override
    public ResponseEntity<StudentResponse> delete(String matricule) {
        return ResponseEntity.ok(studentService.delete(matricule));
    }
}
