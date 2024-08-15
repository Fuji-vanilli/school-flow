import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './guards/auth.guard';
import { SidebarComponent } from './pages/sidebar/sidebar.component';
import path from 'path';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { ClassComponent } from './admin/class/class.component';
import { StudentComponent } from './admin/student/student.component';
import { ProfessorComponent } from './admin/professor/professor.component';
import { TimeTableComponent } from './admin/time-table/time-table.component';
import { EcolageComponent } from './admin/ecolage/ecolage.component';
import { SalaryComponent } from './admin/salary/salary.component';
import { NoteComponent } from './admin/note/note.component';
import { ClassConcilComponent } from './admin/class-concil/class-concil.component';
import { AddClassComponent } from './admin/add-class/add-class.component';
import { AddStudentComponent } from './admin/add-student/add-student.component';
import { ProfileStudentComponent } from './admin/profile-student/profile-student.component';
import { CourseComponent } from './admin/course/course.component';
import { AddProfessorComponent } from './admin/add-professor/add-professor.component';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'admin', component: SidebarComponent, canActivate: [AuthGuard], data: {roles:'ADMIN'}, 
    children: [
      { path: '', component: DashboardComponent },
      { path: 'class', component: ClassComponent },
      { path: 'student', component: StudentComponent },
      { path: 'professor', component: ProfessorComponent },
      { path: 'time-table', component: TimeTableComponent },
      { path: 'ecolage', component: EcolageComponent },
      { path: 'salary', component: SalaryComponent },
      { path: 'note', component: NoteComponent },
      { path: 'class-concil', component: ClassConcilComponent },
      { path: 'add-class', component: AddClassComponent },
      { path: 'add-student', component: AddStudentComponent },
      { path: 'add-student/:classID', component: AddStudentComponent },
      { path: 'profile-student/:studentID', component: ProfileStudentComponent },
      { path: 'add-course/:classID', component: CourseComponent },
      { path: 'add-professor', component: AddProfessorComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
