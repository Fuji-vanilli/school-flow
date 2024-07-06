import { Component, inject, OnInit } from '@angular/core';
import { ClassService } from '../../services/class.service';
import { Class } from '../../models/class.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  classService= inject(ClassService);
  classes: Class[] | undefined;

  ngOnInit(): void {
    this.classService.getAll().subscribe({
      next: classes=> {
        this.classes= classes;
        console.log("all classes getted successfully!");
        
      },
      error: err=> {
        console.log("error: ", err);
        
      }
    })
  }
}
