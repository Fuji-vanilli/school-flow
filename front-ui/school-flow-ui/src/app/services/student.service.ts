import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Student } from '../models/student.model';
import { Observable } from 'rxjs';
import { Note } from '../models/note.model';
import { Ecolage } from '../models/ecolage.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  httpClient= inject(HttpClient);

  url: string= environment.API_URL+'/STUDENT-SERVICE/api/student';

  constructor() { }

  create(student: Student): Observable<any> {
    return this.httpClient.post(this.url+'/create', student);
  }

  update(student: Student): Observable<any> {
    return this.httpClient.put(this.url+'/update', student);
  }

  get(matricule: string): Observable<any> {
    return this.httpClient.get(this.url+'/get/'+matricule);
  }

  getAll(): Observable<any> {
    return this.httpClient.get(this.url+'/all');
  }

  getByClassID(classID: string): Observable<any> {
    return this.httpClient.get(this.url+'/get-by-classID/'+classID);
  }

  addNote(note: Note): Observable<any> {
    return this.httpClient.patch(this.url+'/add-note', note);
  }

  paymentEcolage(ecolage: Ecolage): Observable<any> {
    return this.httpClient.patch(this.url+'/payment-ecolage', ecolage);
  }

  addReport(report: Report): Observable<any> {
    return this.httpClient.patch(this.url+'/add-report', report);
  }

  delete(matricule: string): Observable<any> {
    return this.httpClient.delete(this.url+'/delete/'+matricule);
  }
}
