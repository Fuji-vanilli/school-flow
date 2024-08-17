import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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

  classeSelected = new FormControl('');

  classes: Class[]= [];
  classByID: Class | undefined;
  classID: string | undefined;

  selectedFile!: File;
  filename: string= '';
  selectImage: any;

  ngOnInit(): void {
    this.initFormGroup();
    this.loadClasses();
    this.activatedRoute.paramMap.subscribe(
      params=> {
        this.classID= params.get('classID')!;
        if (this.classID) {
          this.classService.getByID(this.classID).subscribe({
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
      fathersName: this.formBuilder.control(''),
      mothersName: this.formBuilder.control(''),
      classID: this.formBuilder.control('Choisi une classe', Validators.required), 
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
    const classIDForm= this.formGroup.value.classID;
    const student= {
      firstname: this.formGroup.value.firstname,
      lastname: this.formGroup.value.lastname,
      dateOfBirth: this.formGroup.value.dateOfBirth,
      birthPlace: this.formGroup.value.birthPlace,
      email: this.formGroup.value.email,
      phone: this.formGroup.value.phone,
      address: this.formGroup.value.address,
      fathersName: this.formGroup.value.fathersName,
      mothersName: this.formGroup.value.mothersName,
      classID: this.classID!== null? this.classID: classIDForm,
      originSchool: this.formGroup.value.originSchool
    };

    this.studentService.create(student).subscribe({
      next: response=> {
        console.log('new class created successfully');
        Swal.fire('Succes', 'Nouvel élève ajouté avec succès', 'success');
        if (this.classID) {
          this.classService.addStudent(response.id, this.classID).subscribe({
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

  uploadFile() {
    document.getElementById('fileInput')?.click();
  }

  handleFileInput(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.selectImage = e.target.result;
      };
      reader.readAsDataURL(file);
    }

    const target= event.target as HTMLInputElement;
    if (target.files && target.files.length> 0) {
      this.selectedFile= target.files[0];
      this.filename= this.selectedFile.name;
    }
  }

  uploadImage(id: string) {
    this.studentService.uploadImageProfile(this.selectedFile, id).subscribe({
      next: response=> {
        console.log('professor: ', response);
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

  getSelectedClassLevel() {
    return this.classes.filter(aClass => this.classeSelected.value=== aClass.id)
      .map(aClass => aClass.level);
  }

  capitalize(s: string): string {
    return s.charAt(0).toUpperCase()+s.slice(1);
  }

}
