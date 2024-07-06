import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './guards/auth.guard';
import { SidebarComponent } from './pages/sidebar/sidebar.component';
import path from 'path';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { ClassComponent } from './admin/class/class.component';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'admin', component: SidebarComponent, canActivate: [AuthGuard], data: {roles:'ADMIN'}, 
    children: [
      { path: '', component: DashboardComponent },
      { path: 'class', component: ClassComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
