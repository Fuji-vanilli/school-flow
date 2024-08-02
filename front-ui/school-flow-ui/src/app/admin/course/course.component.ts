import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CourseService } from '../../services/course.service';
import { response } from 'express';
import Swal from 'sweetalert2';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Class } from '../../models/class.model';
import { ClassService } from '../../services/class.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrl: './course.component.scss'
})
export class CourseComponent implements OnInit{

  formGroup!: FormGroup;
  formBuilder= inject(FormBuilder);
  route= inject(Router)
  activatedRoute= inject(ActivatedRoute);
  courseService= inject(CourseService);
  classService= inject(ClassService);

  classID: string= '';
  aClass!: Class;

  ngOnInit(): void {
   this.initFormGroup();
   this.activatedRoute.paramMap.subscribe(
    params=> {
      this.classID= params.get('classID')!;
      if (this.classID) {
        this.classService.getByID(this.classID).subscribe({
          next: response=> {
            this.aClass= response;
            console.log('class by id for add student: ', this.aClass);
            
          },
          error: err=> {
            console.log('error: ', err);
            
          }
        })
    }}
   )
   
  }

  initFormGroup() {
    this.formGroup= this.formBuilder.group({
      title: this.formBuilder.control('', Validators.required),
      credit: this.formBuilder.control('', Validators.required),
      professorID: this.formBuilder.control(''),
      hoursPerWeek: this.formBuilder.control('', Validators.required),
      pricePerHour: this.formBuilder.control('', Validators.required)
    })
  }

  createCourse() {
    const course= {
      title: this.formGroup.value.title,
      credit: this.formGroup.value.credit,
      classID: this.classID,
      hoursPerWeek: this.formGroup.value.hoursPerWeek,
      pricePerHour: this.formGroup.value.pricePerHour
    };

    this.courseService.create(course).subscribe({
      next: response=> {
        console.log('created: ', response);
        this.route.navigateByUrl('/admin/class');
        Swal.fire('Success', 'Nouvelle matière crée', 'success');
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }
}
