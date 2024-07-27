import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrl: './course.component.scss'
})
export class CourseComponent implements OnInit{

  formGroup!: FormGroup;
  formBuilder= inject(FormBuilder);

  ngOnInit(): void {
   this.initFormGroup();
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

  }
}
