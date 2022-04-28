import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  upload(formData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/upload`, formData);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.apiUrl}/files`);
  }
}
