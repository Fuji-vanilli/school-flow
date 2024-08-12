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
  classResult: Class[] | undefined;

  length = 50;
  pageSize = 5;
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
        this.length= this.classes?.length!;
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

    const startIndex= this.pageIndex*this.pageSize;
    const endIndex= startIndex+ this.pageSize;

    this.classResult= this.classes?.slice(startIndex, endIndex);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
}
