import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClassService } from '../../services/class.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-class',
  templateUrl: './add-class.component.html',
  styleUrl: './add-class.component.scss'
})
export class AddClassComponent implements OnInit {

  formGroup!: FormGroup;
  classService= inject(ClassService);
  formBuilder= inject(FormBuilder);
  router= inject(Router);

  ngOnInit(): void {
    this.formGroup= this.formBuilder.group({
      level: this.formBuilder.control('', Validators.required),
      section: this.formBuilder.control('PRIMARY', Validators.required),
      maximumCapacity: this.formBuilder.control(0, Validators.required),
      ecolage: this.formBuilder.control('', Validators.required)
    })
  }

  createClass() {
    const aClass= {
      level: this.formGroup.value.level,
      section: this.formGroup.value.section,
      maximumCapacity: this.formGroup.value.maximumCapacity,
      ecolage: this.formGroup.value.ecolage
    };

    this.classService.createClass(aClass).subscribe({
      next: response=> {
        console.log('new class created successfully');
        Swal.fire('Succès', 'nouvelle class créer', 'success');
        this.router.navigateByUrl('/admin/class')
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

}
