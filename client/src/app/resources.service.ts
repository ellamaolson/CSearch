import { Injectable } from '@angular/core';
import { Observable, from, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

export interface Resource {
  pid?: number;
  id?: number;
  title: string;
  category?: string;
  description: string;
  process: string;
  difficulty: number;
  links?: string[];
}

export interface Pair {
  key: Resource;
  value: string[];
}

export interface Link {
  pid?: number;
  link: string;
}

@Injectable({
  providedIn: 'root',
})
export class ResourcesService {
  uri = 'http://ec2-34-201-5-29.compute-1.amazonaws.com';
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
   * Converts a Pair containing a Resources and Link to a Resource with links
   * @param pair
   */
  private convertPairToResource(pair: Pair) {
    const resource: Resource = pair.key;
    resource.links = [];
    pair.value.forEach((l) => resource.links.push(l.link.toString()));
    console.log('Resource ', resource);
    return resource;
  }

  /**
   * Creates a Resouce[] out of a Pair[]
   * @param pairs
   * @return Resource[]
   */
  private createResourceArrayFromPairs(pairs: Pair[]) {
    const resources: Resource[] = [];
    Object.values(pairs).forEach((pair) => {
      resources.push(this.convertPairToResource(pair));
    });

    return resources;
  }

  /**
   * Gets all resources
   */
  getResources(): Observable<Resource[]> {
    return this.http.get<Pair[]>(`${this.uri}/all`, this.getHttpHeaders()).pipe(
      map((pairs) => {
        return this.createResourceArrayFromPairs(Object.values(pairs));
      })
    );
  }

  /**
   * Searches for matching resources
   * Maps the turn Pair key to a resource and the value to resource.links
   * @param searchTerm
   */
  searchForResources(searchTerm: string): Observable<Resource[]> {
    return this.http.get<Pair[]>(`${this.uri}/search/` + searchTerm, this.getHttpHeaders()).pipe(
      map((pairs) => {
        return this.createResourceArrayFromPairs(Object.values(pairs));
      })
    );
  }

  /**
   * Create a new resource
   * @param resource
   */
  createResource(resource: Resource): Observable<Resource> {
    return this.http.post<Resource>(`${this.uri}/csproject`, resource);
  }
}
