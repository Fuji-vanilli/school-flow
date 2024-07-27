import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Course } from '../models/course.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  httpClient= inject(HttpClient);
  url: string= environment.API_URL+'/COURSE-SERVICE/api/course';

  constructor() { }

  create(course: Course): Observable<any> {
    return this.httpClient.put(this.url+'/create', course);
  }

  update(course: Course): Observable<any> {
    return this.httpClient.put(this.url+'/update', course);
  }

  get(id: string): Observable<any> {
    return this.httpClient.get(this.url+'/get/'+id);
  }

  getAll(): Observable<any> {
    return this.httpClient.get(this.url+'/all');
  }
}
