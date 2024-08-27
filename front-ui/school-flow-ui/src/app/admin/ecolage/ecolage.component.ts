import { Component, inject, OnInit } from '@angular/core';
import { Student } from '../../models/student.model';
import { StudentService } from '../../services/student.service';
import { Class } from '../../models/class.model';
import { ClassService } from '../../services/class.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-ecolage',
  templateUrl: './ecolage.component.html',
  styleUrl: './ecolage.component.scss'
})
export class EcolageComponent implements OnInit{
  studentService= inject(StudentService);
  classService= inject(ClassService);

  colors: string[]= ['#FF6B6B', '#00DC82'];

  students: Student[]= [];
  classes: Class[]= [];

  length = 50;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 15];

  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;

  pageEvent!: PageEvent;

  ngOnInit(): void {
    this.loadStudents();
    this.loadClasses();
  }

  loadStudents() {
    this.studentService.getAll().subscribe({
      next: response=> {
        this.students= response;
        console.log('students: ', this.students);
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }


  loadClasses() {
    this.classService.getAll().subscribe({
      next: response=> {
        this.classes= response
        console.log('secondary:', this.classes); 
        
      },
      error: err=> {
        console.log('error: ', err);
        
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

    this.students= this.students?.slice(startIndex, endIndex);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
}
