import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StudentService } from '../../services/student.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ClassService } from '../../services/class.service';
import { Class } from '../../models/class.model';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrl: './add-student.component.scss'
})
export class AddStudentComponent implements OnInit{


  formGroup!: FormGroup;
  studentService= inject(StudentService);
  formBuilder= inject(FormBuilder);
  router= inject(Router);
  classService= inject(ClassService);

  classes: Class[] | undefined;

  ngOnInit(): void {
    this.initFormGroup();
    this.loadClasses();
  }

  initFormGroup() {
    this.formGroup= this.formBuilder.group({
      firstname: this.formBuilder.control('', Validators.required),
      lastname: this.formBuilder.control('', Validators.required),
      dateOfBirth: this.formBuilder.control('', Validators.required),
      birthPlace: this.formBuilder.control('', Validators.required),
      email: this.formBuilder.control('', Validators.required),
      phone: this.formBuilder.control('', Validators.required),
      address: this.formBuilder.control('', Validators.required),
      aClass: this.formBuilder.control('Choisi une classe', Validators.required),
      originSchool: this.formBuilder.control('', Validators.required),
    })
  }

  loadClasses() {
    this.classService.getAll().subscribe({
      next: response=> {
        this.classes= response;
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

  createStudent() {
    const student= {
      firstname: this.formGroup.value.firstname,
      lastname: this.formGroup.value.lastname,
      dateOfBirth: this.formGroup.value.dateOfBirth,
      birthPlace: this.formGroup.value.birthPlace,
      email: this.formGroup.value.email,
      phone: this.formGroup.value.phone,
      address: this.formGroup.value.address,
      aClass: this.formGroup.value.aClass,
      originSchool: this.formGroup.value.originSchool
    };

    this.studentService.create(student).subscribe({
      next: response=> {
        console.log('new class created successfully');
        Swal.fire('Succes', 'Nouvel élèves ajouté avec succès', 'success');
        this.router.navigateByUrl('/admin/class')
      },
      error: err=> {
        console.log('error: ', err);

      }
    })
  }

}
