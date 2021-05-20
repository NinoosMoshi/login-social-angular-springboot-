import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  private baseUrl = 'http://localhost:8080/social';

  constructor(private http: HttpClient) { }

   // http://localhost:8080/social/google
   loginWithGoogle(token: string): Observable<any>{
     return this.http.post(`${this.baseUrl}/google`, {token}).pipe(
       map(response => response)
     );
   }


   // http://localhost:8080/social/facebook
   loginWithFacebook(token: string): Observable<any>{
    return this.http.post(`${this.baseUrl}/facebook`, {token}).pipe(
      map(response => response)
    );
  }



}
