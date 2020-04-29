import { Injectable } from '@angular/core';
import { Observable, from, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export interface Resource {
  pid?: number;
  title: string;
  category?: string;
  description: string;
  process: string;
  difficulty: number;
  links?: string[];
}

@Injectable({
  providedIn: 'root',
})
export class ResourcesService {
  uri = 'http://localhost:8080';
  resourceCollection: Observable<Resource[]>;

  constructor(private http: HttpClient) {}

  /**
   * The HTTP GET headers
   */
  getHttpHeaders() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Set-Cookie': 'HttpOnly;Secure;SameSite=Strict',
      }),
    };
    return httpOptions;
  }

  /**
   * Gets all resources
   */
  getResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(`${this.uri}/all`, this.getHttpHeaders());
  }

  /**
   * Searches for matching resources
   * @param searchTerm
   */
  searchForResources(searchTerm: string): Observable<Resource[]> {
    return this.http.get<Resource[]>(`${this.uri}/search/` + searchTerm, this.getHttpHeaders());
  }

  createResource(resource: Resource): Observable<Resource> {
    return this.http.post<Resource>(`${this.uri}/csproject`, resource);
  }
}
