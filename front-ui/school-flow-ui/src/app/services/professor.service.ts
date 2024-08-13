import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Professor } from '../models/professor.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {
  httpClient= inject(HttpClient);
  url: string= environment.API_URL+'/api/professor/';

  constructor() { }

  create(professor: Professor): Observable<any> {
    return this.httpClient.post(this.url+'create', professor);
  }

  update(professor: Professor): Observable<any> {
    return this.httpClient.put(this.url+'update', professor);
  }

  getAll(): Observable<any> {
    return this.httpClient.get(this.url+'all');
  }
 }