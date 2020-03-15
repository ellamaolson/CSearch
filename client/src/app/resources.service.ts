import { Injectable } from '@angular/core';
import { Observable, from, of } from 'rxjs';

export interface Resource {
  name: string;
  description: string;
}

@Injectable({
  providedIn: 'root',
})
export class ResourcesService {
  resourceCollection: Observable<Resource[]>;

  constructor() {
    const resource1: Resource = {
      name: 'Google',
      description: 'A search engine for browsing and finding resources on the internet',
    };
    const resource2: Resource = {
      name: 'Facebook',
      description: 'A social media platform for keeping up with friends.',
    };
    const resource3: Resource = {
      name: 'Apple',
      description: 'A technology company that creates computers and phones.',
    };

    this.resourceCollection = of([resource1, resource2, resource3]);
  }

  getResources(): Observable<Resource[]> {
    return this.resourceCollection;
  }

  /**
   * Separates a string into a string array
   * @param term
   */
  parseStringToArray(term: string): string[] {
    console.log('Submission: Search Term string: ', term);
    return term.split(' ').map((item: string) => item.trim());
  }
}
