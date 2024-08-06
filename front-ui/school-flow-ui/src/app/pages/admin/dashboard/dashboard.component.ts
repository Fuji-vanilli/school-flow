import { ChangeDetectionStrategy, Component, inject, model, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ClassService } from '../../../services/class.service';
import { Class } from '../../../models/class.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class DashboardComponent implements OnInit{

  selected = model<Date | null>(null);

  classService= inject(ClassService);

  classes: Class[] | undefined;

  ngOnInit(): void {
    this.loadClasses();
  }

  loadClasses() {
    console.time('loadClasses');
    this.classService.getAll().subscribe({
      next: classes=> {
        this.classes= classes; 
        console.log("classes: ", classes); 
        console.timeEnd('loadClasses')
        
      },
      error: err=> {
        console.log("error: ", err);
        
      }
    })
  }
}
