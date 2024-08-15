import { Component, inject, OnInit } from '@angular/core';
import { Professor } from '../../models/professor.model';
import { ProfessorService } from '../../services/professor.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import Swal from 'sweetalert2';

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

  capitalize(s: string): string {
    return s.charAt(0).toUpperCase()+s.slice(1);
  }

}
