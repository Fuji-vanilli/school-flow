import { Component, inject, OnInit } from '@angular/core';
import { Student } from '../../models/student.model';
import { StudentService } from '../../services/student.service';
import { Class } from '../../models/class.model';
import { ClassService } from '../../services/class.service';

@Component({
  selector: 'app-ecolage',
  templateUrl: './ecolage.component.html',
  styleUrl: './ecolage.component.scss'
})
export class EcolageComponent implements OnInit{
  studentService= inject(StudentService);
  classService= inject(ClassService);

  colors: string[]= ['#FF6B6B', '#00DC82'];

  students: Student[]= [];
  classes: Class[]= [];

  ngOnInit(): void {
    this.loadStudents();
    this.loadClasses();
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


  loadClasses() {
    this.classService.getAll().subscribe({
      next: response=> {
        this.classes= response
        console.log('secondary:', this.classes); 
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }


}
