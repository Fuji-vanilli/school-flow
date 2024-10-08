package com.fuji.student_service.controller;

import com.fuji.student_service.DTO.StudentRequest;
import com.fuji.student_service.DTO.StudentResponse;
import com.fuji.student_service.models.Ecolage;
import com.fuji.student_service.models.Note;
import com.fuji.student_service.models.Report;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface StudentController {
    @PostMapping("create")
    ResponseEntity<StudentResponse> create(@RequestBody StudentRequest request);
    @PutMapping("update")
    ResponseEntity<StudentResponse> update(@RequestBody StudentRequest request);
    @GetMapping("get-by-id/{id}")
    ResponseEntity<StudentResponse>  get(@PathVariable String id);
    @GetMapping("get-by-matricule/{matricule}")
    ResponseEntity<StudentResponse>  getByMatricule(@PathVariable String matricule);
    @GetMapping("all")
    ResponseEntity<List<StudentResponse>> getAll();
    @PostMapping("all-by-ids")
    ResponseEntity<List<StudentResponse>> getAllByIds(@RequestBody List<String> ids);
    @GetMapping("get-by-classID/{classID}")
    ResponseEntity<List<StudentResponse>> getAllByClassID(@PathVariable String classID);
    @PatchMapping("add-note")
    ResponseEntity<StudentResponse>  addNotes(@RequestBody Note note);
    @PatchMapping("payment-ecolage")
    ResponseEntity<StudentResponse>  paymentEcolage(@RequestBody Ecolage ecolage);
    @PatchMapping("add-report")
    ResponseEntity<StudentResponse> addReport(@RequestBody Report report);
    @DeleteMapping("delete/{matricule}")
    ResponseEntity<StudentResponse> delete(@PathVariable String matricule);
}
