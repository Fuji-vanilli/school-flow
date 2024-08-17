import { Component, inject, OnInit } from '@angular/core';
import { Professor } from '../../models/professor.model';
import { ProfessorService } from '../../services/professor.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { Course } from '../../models/course.model';
import { Class } from '../../models/class.model';
import { ClassService } from '../../services/class.service';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrl: './professor.component.scss'
})
export class ProfessorComponent implements OnInit {
  professors: Professor[] | undefined;
  professorService= inject(ProfessorService);
  classService= inject(ClassService);
  courseService= inject(CourseService);
  formBuilder= inject(FormBuilder);

  formGroup!: FormGroup;

  classesSelected = new FormControl<string[]>([]);
  coursesSelected = new FormControl<string[]>([]);

  courses: Course[]= [];
  classes: Class[]= [];

  profShowed!: Professor;
  professorToUpdate!: Professor;

  ngOnInit(): void {
    this.initFormGroup();
    this.loadProfessors();
    this.loadClasses();
    this.loadCourses();
  }

  loadProfessors() {
    this.professorService.getAll().subscribe({
      next: response=> {
        this.professors= response;
        console.log('professors: ', this.professors);
        
      },
      error: err=> {
        console.log(err);
        
      }
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

  getSelectedCourseTitles() {
    return this.courses.filter(course => this.coursesSelected.value?.includes(course.id!))
      .map(course => course.title);
  }

  getSelectedClassLevel() {
    return this.classes.filter(aClass => this.classesSelected.value?.includes(aClass.id!))
      .map(aClass => aClass.level);
  }

  toUpdate(professor: Professor) {
    this.professorToUpdate= professor;
    this.formGroup= this.formBuilder.group({
      firstname: this.formBuilder.control(professor.firstname),
      lastname: this.formBuilder.control(professor.lastname),
      dateOfBirth: this.formBuilder.control(professor.dateOfBirth),
      birthPlace: this.formBuilder.control(professor.dateOfBirth),
      email: this.formBuilder.control(professor.email),
      phone: this.formBuilder.control(professor.phone),
      genre: this.formBuilder.control(professor.genre),
      address: this.formBuilder.control(professor.address),
      degree: this.formBuilder.control(professor.degree),
      hoursRate: this.formBuilder.control(professor.hoursRate)
    })
  }

  updateProfessor() {
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
      courseIDs: this.coursesSelected.value || [],
      classIDs: this.classesSelected.value || []
    };

    this.professorService.update(professor).subscribe({
      next: response=> {
        console.log('response: ', response);
        Swal.fire('Succès', 'Prof mis à jour avec succès', 'success');
        
      },
      error: err=> {
        console.log('error: ',err);
        
      }
    })
  }

  delete(id: string) {
    Swal.fire({
      title: 'Attention!',
      text: 'Vous êtes sûr de suprimer cette Professeur!?',
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
        this.professorService.delete(id).subscribe({
          next: response=> {
            console.log('prof deleted successfully');
            Swal.fire('Suprimé', 'Professeur suprimé avec succès!', 'success');
            this.loadProfessors();
            console.log("status", response.statusCode);
          },
          error: err=> {
            console.log('error: ', err);
            
          }
        })
    }
  })
  }

  selectProf(prof: Professor) {
    this.profShowed= prof;
  }

  capitalize(s: string): string {
    return s.charAt(0).toUpperCase()+s.slice(1);
  }

}
