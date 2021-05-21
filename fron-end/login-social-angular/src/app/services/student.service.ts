import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Student } from '../model/student';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class StudentService {




  private baseUrl = 'http://localhost:8080/api/students'

  constructor(private http: HttpClient) { }


 getStudents(): Observable<Student[]>{
   let head = new HttpHeaders().set("Authorization", sessionStorage.getItem('token'))
   return this.http.get<Student[]>(this.baseUrl, {headers:head}).pipe(
     map(response =>{
        return response;
     })
   )
 }


}
