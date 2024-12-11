import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  // constructor() {}
  private apiUrl = '/api/register';
  constructor(private http: HttpClient) {}
  registerUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user);
  }
}
