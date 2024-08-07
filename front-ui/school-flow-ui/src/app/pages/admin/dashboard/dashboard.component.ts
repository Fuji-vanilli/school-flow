import { ChangeDetectionStrategy, Component, inject, model, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ClassService } from '../../../services/class.service';
import { Class } from '../../../models/class.model';
import { PageEvent } from '@angular/material/paginator';

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

  length = 50;
  pageSize = 10;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 25];

  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;

  pageEvent!: PageEvent;

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

  handlePageEvent(e: PageEvent) {
    this.pageEvent = e;
    this.length = e.length;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
}
