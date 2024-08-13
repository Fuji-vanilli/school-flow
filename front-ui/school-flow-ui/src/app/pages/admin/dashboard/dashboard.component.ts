import { ChangeDetectionStrategy, Component, inject, model, OnInit, TemplateRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { ClassService } from '../../../services/class.service';
import { Class } from '../../../models/class.model';
import { PageEvent } from '@angular/material/paginator';
import { MatDatepicker } from '@angular/material/datepicker';
import { FormControl } from '@angular/forms';
import * as _moment from 'moment';
import {default as _rollupMoment, Moment} from 'moment';

const moment = _rollupMoment || _moment;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})

export class DashboardComponent implements OnInit{
  colors: string[]= [
    '#2E8B57',
    '#FFA500',
    '#a364ff',
    '#61bc84',
    '#dd8900',
    '#cb80ff',
    '#8FBC8F'  
  ]

  date = new FormControl(moment());

  selected = model<Date | null>(null);

  classService= inject(ClassService);

  classes: Class[] | undefined;
  classResult: Class[] | undefined;

  length = 50;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 15];

  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;

  pageEvent!: PageEvent;
  dateTime: any;

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
        
        this.classResult= this.classes?.slice(0, 5);
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

  capitalize(s: string): string {
    return s.charAt(0).toUpperCase()+ s.slice(1);
  }

  setMonthAndYear(normalizedMonthAndYear: Moment, datepicker: MatDatepicker<Moment>) {
    const ctrlValue = this.date.value!;
    ctrlValue.month(normalizedMonthAndYear.month());
    ctrlValue.year(normalizedMonthAndYear.year());
    this.date.setValue(ctrlValue);
    datepicker.close();
  }
}
