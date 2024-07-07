import { Component, inject, OnInit } from '@angular/core';
import { ClassService } from '../../services/class.service';
import { Class } from '../../models/class.model';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrl: './class.component.scss'
})
export class ClassComponent implements OnInit{
  classService= inject(ClassService);
  router= inject(Router);

  classes: Class[] | undefined;

  ngOnInit(): void {
    this.loadClasses();
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
            Swal.fire('Success', 'category deleted successfully!', 'success');
            this.loadClasses();
            console.log("status", response.statusCode);
          }
        })
    }
  })
  }
}
