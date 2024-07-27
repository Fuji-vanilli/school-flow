import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HeaderComponent } from './layout/header/header.component';
import { NavbarComponent } from './layout/header/navbar/navbar.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { HomeComponent } from './pages/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { SidebarComponent } from './pages/sidebar/sidebar.component';
import { ClassComponent } from './admin/class/class.component';
import { StudentComponent } from './admin/student/student.component';
import { ProfessorComponent } from './admin/professor/professor.component';
import { TimeTableComponent } from './admin/time-table/time-table.component';
import { EcolageComponent } from './admin/ecolage/ecolage.component';
import { SalaryComponent } from './admin/salary/salary.component';
import { NoteComponent } from './admin/note/note.component';
import { ClassConcilComponent } from './admin/class-concil/class-concil.component';
import { AddClassComponent } from './admin/add-class/add-class.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BaseChartDirective, provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { AddStudentComponent } from './admin/add-student/add-student.component';
import { ProfileStudentComponent } from './admin/profile-student/profile-student.component';
import { CourseComponent } from './admin/course/course.component';

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'school-flow-realm',
        clientId: 'school-flow-client'
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavbarComponent,
    HomeComponent,
    DashboardComponent,
    SidebarComponent,
    ClassComponent,
    StudentComponent,
    ProfessorComponent,
    TimeTableComponent,
    EcolageComponent,
    SalaryComponent,
    NoteComponent,
    ClassConcilComponent,
    AddClassComponent,
    AddStudentComponent,
    ProfileStudentComponent,
    CourseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    KeycloakAngularModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BaseChartDirective
  ],
  providers: [
    provideClientHydration(),
    provideCharts(withDefaultRegisterables()),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
