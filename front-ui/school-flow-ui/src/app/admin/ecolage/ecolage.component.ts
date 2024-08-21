import { Component, inject, OnInit } from '@angular/core';
import { Student } from '../../models/student.model';
import { StudentService } from '../../services/student.service';

@Component({
  selector: 'app-ecolage',
  templateUrl: './ecolage.component.html',
  styleUrl: './ecolage.component.scss'
})
export class EcolageComponent implements OnInit{
  studentService= inject(StudentService);

  students: Student[]= [];

  ngOnInit(): void {

  }

  loadStudents() {
    this.studentService.getAll().subscribe({
      next: response=> {
        this.students= response;
        console.log('students: ', this.students);
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

}
