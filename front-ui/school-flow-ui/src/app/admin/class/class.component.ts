import { Component, inject, OnInit } from '@angular/core';
import { ClassService } from '../../services/class.service';
import { Class } from '../../models/class.model';
import { Router } from '@angular/router';

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
    this.classService.delete(id).subscribe({
      next: response=> {
        console.log('class deleted successfully');
        this.router.navigateByUrl('/admin/class');
      }
    })
  }
}
