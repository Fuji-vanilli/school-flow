import { Component, inject, OnInit } from '@angular/core';
import { StudentService } from '../../services/student.service';
import { Student } from '../../models/student.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClassService } from '../../services/class.service';
import { Class } from '../../models/class.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrl: './student.component.scss'
})
export class StudentComponent implements OnInit {
  studentService= inject(StudentService);
  classService= inject(ClassService);
  formBuilder= inject(FormBuilder);

  students: Student[] | undefined;
  classes: Class[] | undefined;
  formGroup!: FormGroup;
  selectedIDStudent!: string;
  
  ngOnInit(): void {
    this.loadStudents();
    this.loadClasses();
    this.initFormGroup();
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
        this.classes= response;
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

  update(student: Student) {
    this.selectedIDStudent= student.id!;
    this.formGroup= this.formBuilder.group({
      firstname: this.formBuilder.control(student.firstname, Validators.required),
      lastname: this.formBuilder.control(student.lastname, Validators.required),
      dateOfBirth: this.formBuilder.control(student.dateOfBirth, Validators.required),
      birthPlace: this.formBuilder.control(student.birthPlace, Validators.required),
      email: this.formBuilder.control(student.email, Validators.required),
      phone: this.formBuilder.control(student.phone, Validators.required),
      address: this.formBuilder.control(student.address, Validators.required),
      aClass: this.formBuilder.control(student.aClass?.id, Validators.required),
      originSchool: this.formBuilder.control(student.originSchool, Validators.required),
    })
  }

  updateStudent() {
    const aClass= this.classes?.find(c=> c.id=== this.formGroup.value.id);
    const student= {
      id: this.selectedIDStudent,
      firstname: this.formGroup.value.firstname,
      lastname: this.formGroup.value.lastname,
      dateOfBirth: this.formGroup.value.dateOfBirth,
      birthPlace: this.formGroup.value.birthPlace,
      email: this.formGroup.value.email,
      phone: this.formGroup.value.phone,
      address: this.formGroup.value.address,
      aClass: aClass,
      originSchool: this.formGroup.value.originSchool
    }

    this.studentService.update(student).subscribe({
      next: response=> {
        console.log('updated: ', response);
        this.loadStudents();
        Swal.fire('Succès', 'Elève mis à jour avec Succès', 'success');
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })

  }

  delete(matricule: string) {
    Swal.fire({
      title: 'Attention!',
      text: 'Vous êtes sûr de suprimer cette étudiant!?',
      icon: 'warning',
      showCancelButton: true,
      cancelButtonText: 'Anuler',
      cancelButtonColor: 'rgba(0, 0, 0, 0.3)',
      confirmButtonText: 'Suprimer',
      confirmButtonColor: '#bb2649',
      background: '#1E293B',
      color: '#fff'
    }).then(result=> {
       if (result.isConfirmed) {
        this.studentService.delete(matricule).subscribe({
          next: response=> {
            console.log('student deleted successfully');
            Swal.fire('Success', 'Etudiant suprimé avec succès!', 'success');
            this.loadStudents();
            console.log("status", response.statusCode);
          },
          error: err=> {
            console.log('error: ', err);
            
          }
        })
    }
  })
  }

  capitalize(s: string): string {
    return s.charAt(0).toUpperCase()+s.slice(1);
  }

}
