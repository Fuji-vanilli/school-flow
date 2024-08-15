import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-professor',
  templateUrl: './add-professor.component.html',
  styleUrl: './add-professor.component.scss'
})
export class AddProfessorComponent implements OnInit{
  formBuilder= inject(FormBuilder);

  formGroup!: FormGroup;

  ngOnInit(): void {
    
  }

  initFormGroup() {
    this.formGroup= this.formBuilder.group({
      firstname: this.formBuilder.control('', Validators.required),
      lastname: this.formBuilder.control(''),
      dateOfBirth: this.formBuilder.control('', Validators.required),
      
    })
  }
}
