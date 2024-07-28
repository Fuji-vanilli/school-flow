import { Component, inject, OnInit } from '@angular/core';
import { ClassService } from '../../services/class.service';
import { Class } from '../../models/class.model';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { access } from 'fs';

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrl: './class.component.scss'
})
export class ClassComponent implements OnInit{
  classService= inject(ClassService);
  router= inject(Router);
  formBuilder= inject(FormBuilder);

  classSelected: Class | undefined;

  classes: Class[] | undefined;

  formGroup!: FormGroup;

  ngOnInit(): void {
    this.loadClasses();
    this.initFormGroup();
  }
  
  initFormGroup() {
    this.formGroup= this.formBuilder.group({
      level: this.formBuilder.control('', Validators.required),
      section: this.formBuilder.control('PRIMARY', Validators.required),
      maximumCapacity: this.formBuilder.control(0, Validators.required),
      ecolage: this.formBuilder.control('', Validators.required)
    })
  }

  loadClasses() {
    this.classService.getAll().subscribe({
      next: classes=> {
        this.classes= classes; 
        console.log("classes: ", classes); 
      },
      error: err=> {
        console.log("error: ", err);
        
      }
    })
  }

  showCourses() {
    
  }

  update(aClass: Class) {
    this.classSelected= aClass;
    this.formGroup= this.formBuilder.group({
      level: this.formBuilder.control(aClass.level, Validators.required),
      section: this.formBuilder.control(aClass.section, Validators.required),
      maximumCapacity: this.formBuilder.control(aClass.maximumCapacity, Validators.required),
      ecolage: this.formBuilder.control(aClass.ecolage, Validators.required)
    })
  }

  updateClass() {
    const aClass= {
      id: this.classSelected?.id,
      level: this.formGroup.value.level,
      section: this.formGroup.value.section,
      maximumCapacity: this.formGroup.value.maximumCapacity,
      ecolage: this.formGroup.value.ecolage
    }

    this.classService.updateClass(aClass).subscribe({
      next: response=> {
        Swal.fire('Succès', 'Class mis à jour avec succès', 'success');
        this.loadClasses();
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

  deleteClass(id: string) {
    Swal.fire({
      title: 'Attention!',
      text: 'Vous êtes sûr de suprimer cette classe!?',
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
        this.classService.delete(id).subscribe({
          next: response=> {
            console.log('class deleted successfully');
            Swal.fire('Success', 'Class suprimé avec succès!', 'success');
            this.loadClasses();
            console.log("status", response.statusCode);
          },
          error: err=> {
            console.log('error: ', err);
            
          }
        })
    }
  })
  }
}
