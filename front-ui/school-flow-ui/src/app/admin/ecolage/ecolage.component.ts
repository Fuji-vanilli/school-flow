import { Component, inject, OnInit } from '@angular/core';
import { Student } from '../../models/student.model';
import { StudentService } from '../../services/student.service';
import { Class } from '../../models/class.model';
import { ClassService } from '../../services/class.service';
import { PageEvent } from '@angular/material/paginator';
import { access } from 'fs';

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
        this.initPageIndexClass();
        
      },
      error: err=> {
        console.log('error: ', err);
        
      }
    })
  }

  initPageIndexClass() {
    this.classes.forEach(aClass=> {
      aClass.pageSize= 5,
      aClass.pageIndex= 0
    })
  }

  handlePageEvent(e: PageEvent, index: number) {
    const aClass= this.classes[index];
    
    aClass.pageSize= e.pageSize;
    aClass.pageIndex= e.pageIndex;

    const startIndex= aClass.pageIndex*aClass.pageSize;
    const endIndex= startIndex+ aClass.pageSize;

    aClass.paginatedStudents= aClass.students?.slice(startIndex, endIndex);
  }

  getPaginatedStudents(aClass: Class) {
    if (!aClass.paginatedStudents) {
      const startIndex= aClass.pageIndex!*aClass.pageSize!;
      const endIndex= startIndex+ aClass.pageSize!;

      aClass.paginatedStudents= aClass.students?.slice(startIndex, endIndex);
    }

    return aClass.paginatedStudents; 
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
}
