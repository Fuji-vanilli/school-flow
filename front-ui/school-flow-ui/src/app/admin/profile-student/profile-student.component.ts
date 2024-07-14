import { Component, inject, OnInit } from '@angular/core';
import { StudentService } from '../../services/student.service';
import { ActivatedRoute } from '@angular/router';
import { Student } from '../../models/student.model';

@Component({
  selector: 'app-profile-student',
  templateUrl: './profile-student.component.html',
  styleUrl: './profile-student.component.scss'
})
export class ProfileStudentComponent implements OnInit{

  studentService= inject(StudentService);
  activatedRoute= inject(ActivatedRoute);

  student: Student | undefined;
  filename: string= '';
  selectImage: string= '';

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      params=> {
        const studentID= params.get('studentID');

        if (studentID) {
          this.studentService.get(studentID).subscribe({
            next: response=> {
              this.student= response;
              console.log('student: ', this.student);
              
            },
            error: err=> {
              console.log('error: ', err);
              
            }
          })
        }
      }
    )
  }

  handleFileInput(event: any) {

  }

  uploadFile() {

  }

  uploadProfileImage() {
    
  }

  capitalize(s: string): string {
    return s.charAt(0).toUpperCase()+s.slice(1);
  }

}
