import { Component, inject, OnInit } from '@angular/core';
import { StudentService } from '../../services/student.service';
import { Student } from '../../models/student.model';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrl: './student.component.scss'
})
export class StudentComponent implements OnInit {
  studentService= inject(StudentService);

  students: Student[] | undefined;

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents() {
    this.studentService.getAll().subscribe({
      next: response=> {
        this.students= this.students;
        console.log('students: ', this.students);
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

  update() {

  }

  delete() {
    
  }

}
