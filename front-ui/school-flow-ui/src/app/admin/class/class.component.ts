import { Component, inject, OnInit } from '@angular/core';
import { ClassService } from '../../services/class.service';
import { Class } from '../../models/class.model';

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrl: './class.component.scss'
})
export class ClassComponent implements OnInit{
  classService= inject(ClassService);

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

}
