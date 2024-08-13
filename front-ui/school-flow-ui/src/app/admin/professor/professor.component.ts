import { Component, inject, OnInit } from '@angular/core';
import { Professor } from '../../models/professor.model';
import { ProfessorService } from '../../services/professor.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrl: './professor.component.scss'
})
export class ProfessorComponent implements OnInit {
  professors: Professor[] | undefined;
  professorService= inject(ProfessorService);
  formBuilder= inject(FormBuilder);

  formGroup!: FormGroup;

  ngOnInit(): void {
    this.initFormGroup();
    this.loadProfessors();
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

  initFormGroup() {
    this.formGroup= this.formBuilder.group({

    })
  }

  create() {

  }

  updateProfessor() {
    
  }
  update(professor: Professor) {

  }

  delete(id: string) {

  }

  capitalize(s: string): string {
    return s.charAt(0).toUpperCase()+s.slice(1);
  }

}
