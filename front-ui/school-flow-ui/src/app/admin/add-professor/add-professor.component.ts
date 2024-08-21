import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ProfessorService } from '../../services/professor.service';
import { CourseService } from '../../services/course.service';
import { Course } from '../../models/course.model';
import { Class } from '../../models/class.model';
import { ClassService } from '../../services/class.service';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import { access } from 'node:fs';
import { response } from 'express';

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
  activatedRoute= inject(ActivatedRoute);

  formGroup!: FormGroup;
  classesSelected = new FormControl<string[]>([]);
  coursesSelected = new FormControl<string[]>([]);

  courses: Course[]= [];
  classes: Class[]= [];

  selectedFile!: File;
  filename: string= '';
  selectImage: any;
  classID!: string;
  classLevel!: String;

  ngOnInit(): void {
    this.initFormGroup();
    this.loadClasses();
    this.loadCourses();
    this.activatedRoute.paramMap.subscribe(
      params=> {
        this.classID= params.get('classID')!;
        console.log('class id: ', this.classID);
        
        if (this.classID) {
          this.classService.getByID(this.classID).subscribe({
            next: response=> {
              this.classLevel= response.level;
              console.log('class by id for add student: ', this.classLevel);
              
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
      lastname: this.formBuilder.control(''),
      dateOfBirth: this.formBuilder.control('', Validators.required),
      birthPlace: this.formBuilder.control('', Validators.required),
      email: this.formBuilder.control(''),
      phone: this.formBuilder.control('', Validators.required),
      genre: this.formBuilder.control('', Validators.required),
      address: this.formBuilder.control('', Validators.required),
      degree: this.formBuilder.control('', Validators.required),
      hoursRate: this.formBuilder.control('', Validators.required)
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

  getSelectedCourseTitles() {
    return this.courses.filter(course => this.coursesSelected.value?.includes(course.id!))
      .map(course => course.title);
  }

  getSelectedClassLevel() {
    return this.classes.filter(aClass => this.classesSelected.value?.includes(aClass.id!))
      .map(aClass => aClass.level);
  }

  createProfessor() {
    let courseIDs= this.coursesSelected.value;
    
    if (this.classID) {
      courseIDs= [this.classID];
    }

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
      hoursRate: this.formGroup.value.hoursRate,
      courseIDs: courseIDs || [],
      classIDs: this.classesSelected.value || []
    }

    

    this.professorService.create(professor).subscribe({
      next: response=> {
        console.log('created successfully: ', response);
        this.uploadImage(response.id);

        Swal.fire('Succès', 'Nouvel Prof enregistrer avec succès', 'success');
        this.route.navigateByUrl('/admin/professor')
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
    this.professorService.uploadImageProfile(this.selectedFile, id).subscribe({
      next: response=> {
        console.log('professor: ', response);
        
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
