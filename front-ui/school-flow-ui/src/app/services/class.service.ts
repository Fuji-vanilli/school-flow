import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { Class } from '../models/class.model';

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  httpClient= inject(HttpClient);
  url: string= environment.API_URL+'/CLASS-SERVICE/api/class';
  constructor() { }

  createClass(aClass: Class): Observable<any> {
    return this.httpClient.post(this.url+'/create', aClass);
  }

  updateClass(aClass: Class): Observable<any> {
    return this.httpClient.put(this.url+'/update', aClass);
  }

  getByID(id: string): Observable<any> {
    return this.httpClient.get(this.url+'/get-by-id/'+id);
  }

  getAll(): Observable<any> {
    return this.httpClient.get(this.url+'/all');
  }

  addStudent(studentID: string, classID: string): Observable<any> {
    const params= {
      studentID: studentID,
      classID: classID
    }

    return this.httpClient.patch(this.url+'/add-student', params);
  }

  addProfessor(professorID: string, classID: string): Observable<any> {
    const params= {
      studentID: professorID,
      classID: classID
    }

    return this.httpClient.patch(this.url+'/add-professor', params);
  }

  delete(id: string): Observable<any> {
    return this.httpClient.delete(this.url+'/delete/'+id);
  }
}
