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
  constructor() { }

  createClass(aClass: Class): Observable<any> {
    return this.httpClient.post(environment.API_URL+'/CLASS-SERVICE/api/class/create', aClass);
  }

  getAll(): Observable<any> {
    return this.httpClient.get(environment.API_URL+'/CLASS-SERVICE/api/class/all');
  }

  delete(id: string): Observable<any> {
    return this.httpClient.delete(environment.API_URL+'/CLASS-SERVICE/api/class/delete/'+id);
  }
}
