import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CourseService } from '../../services/course.service';
import { response } from 'express';
import Swal from 'sweetalert2';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrl: './course.component.scss'
})
export class CourseComponent implements OnInit{

  formGroup!: FormGroup;
  formBuilder= inject(FormBuilder);
  activatedRoute= inject(ActivatedRoute);
  courseService= inject(CourseService);

  classID: string= '';

  ngOnInit(): void {
   this.initFormGroup();
   this.classID= this.activatedRoute.snapshot.paramMap.get('classID') || '';
   console.log('classID: ', this.classID);
   
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
        Swal.fire('Success', 'Nouvelle matière crée', 'success');
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }
}
