import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ProfessorService } from '../../services/professor.service';
import { CourseService } from '../../services/course.service';
import { Course } from '../../models/course.model';
import { Class } from '../../models/class.model';
import { ClassService } from '../../services/class.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

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
  route= inject(Router);

  formGroup!: FormGroup;
  classesSelected = new FormControl([]);
  coursesSelected = new FormControl([]);

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
      birthPlace: this.formBuilder.control('', Validators.required),
      email: this.formBuilder.control(''),
      phone: this.formBuilder.control('', Validators.required),
      genre: this.formBuilder.control('', Validators.required),
      address: this.formBuilder.control('', Validators.required),
      degree: this.formBuilder.control('', Validators.required)
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

  createProfessor() {
    const professor= {
      firstname: this.formGroup.value.firstname,
      lastname: this.formGroup.value.lastname,
      dateOfBirth: this.formGroup.value.dateOfBirth,
      birthPlace: this.formGroup.value.birthPlace,
      email: this.formGroup.value.email,
      phone: this.formGroup.value.phone,
      address: this.formGroup.value.address,
      degree: this.formGroup.value.degree,
      genre: this.formGroup.value.genre,
      courseIDs: this.coursesSelected.value || [],
      classIDs: this.classesSelected.value || []
    }

    this.professorService.create(professor).subscribe({
      next: response=> {
        console.log('created successfully: ', response);
        Swal.fire('Succès', 'Nouvel Prof enregistrer avec succès', 'success');
        this.route.navigateByUrl('/admin/professor')
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

  capitalize(s: string): string {
    return s.charAt(0).toUpperCase()+s.slice(1);
  }
}
