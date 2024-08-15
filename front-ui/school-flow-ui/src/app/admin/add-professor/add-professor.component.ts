import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfessorService } from '../../services/professor.service';
import { CourseService } from '../../services/course.service';
import { Course } from '../../models/course.model';
import { Class } from '../../models/class.model';
import { ClassService } from '../../services/class.service';

@Component({
  selector: 'app-add-professor',
  templateUrl: './add-professor.component.html',
  styleUrl: './add-professor.component.scss'
})
export class AddProfessorComponent implements OnInit{
  formBuilder= inject(FormBuilder);
  professorService= inject(ProfessorService);
  courseService= inject(CourseService);
  classService= inject(ClassService);

  formGroup!: FormGroup;

  courses: Course[] | undefined;
  classes: Class[] | undefined;

  ngOnInit(): void {
    this.initFormGroup();
    this.loadClasses();
    this.loadCourses();
  }

  initFormGroup() {
    this.formGroup= this.formBuilder.group({
      firstname: this.formBuilder.control('', Validators.required),
      lastname: this.formBuilder.control(''),
      dateOfBirth: this.formBuilder.control('', Validators.required),

    })
  }

  loadCourses() {
    this.courseService.getAll().subscribe({
      next: response=> {
        this.courses= response;
        console.log('courses: ', this.courses);
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

  loadClasses() {
    this.classService.getAll().subscribe({
      next: response=> {
        this.classes= response;
        console.log('classes: ', this.classes);
        
      }, 
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }
}
