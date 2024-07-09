import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StudentService } from '../../services/student.service';
import { ActivatedRoute, Router } from '@angular/router';
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
  activatedRoute= inject(ActivatedRoute);
  classService= inject(ClassService);

  classes: Class[] | undefined;
  classByID: Class | undefined;
  classID: string | undefined;

  ngOnInit(): void {
    this.initFormGroup();
    this.loadClasses();
    this.activatedRoute.paramMap.subscribe(
      params=> {
        const classID= params.get('classID')!;
        if (classID) {
          this.classService.getByID(classID).subscribe({
            next: response=> {
              this.classByID= response;
              console.log('class by id for add student: ', this.classByID);
              
            },
            error: err=> {
              console.log('error: ', err);
              
            }
          })
        }
      }
    )
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
    let aClass= this.classes?.find(c=> c.id=== this.formGroup.value.aClass);
    if (this.classByID) {
      aClass= this.classByID;
    }
    
    const student= {
      firstname: this.formGroup.value.firstname,
      lastname: this.formGroup.value.lastname,
      dateOfBirth: this.formGroup.value.dateOfBirth,
      birthPlace: this.formGroup.value.birthPlace,
      email: this.formGroup.value.email,
      phone: this.formGroup.value.phone,
      address: this.formGroup.value.address,
      aClass: aClass,
      originSchool: this.formGroup.value.originSchool
    };

    this.studentService.create(student).subscribe({
      next: response=> {
        console.log('new class created successfully');
        Swal.fire('Succes', 'Nouvel élève ajouté avec succès', 'success');
        if (aClass?.id) {
          this.classService.addStudent(response.id, aClass.id).subscribe({
            next: () => {
              console.log('Student added to class successfully');
              this.router.navigateByUrl('/admin/student');
            },
            error: err => {
              console.log('Error adding student to class: ', err);
              Swal.fire('Error', 'Failed to add student to class', 'error');
            }
          });
        } else {
          this.router.navigateByUrl('/admin/student');
        }
      },
      error: err=> {
        console.log('error: ', err); 

      }
    })
  }

}
