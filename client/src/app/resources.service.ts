import { Injectable } from '@angular/core';
import { Observable, from, of } from 'rxjs';

export interface Resource {
  name: string;
  category: string;
  description: string;
  links: string[];
}

@Injectable({
  providedIn: 'root',
})
export class ResourcesService {
  resourceCollection: Observable<Resource[]>;

  constructor() {
    const resource1: Resource = {
      name: 'Java REST Api using Spring Boot',
      category: 'Enterprise Software',
      description: 'Quickest Way to Create REST API in Java with Spring Boot',
      links: ['https://www.javadevjournal.com/spring-boot/spring-boot-mysql/'],
    };
    const resource2: Resource = {
      name: 'AWS Dynamo Database setup',
      category: 'Enterprise Software',
      description:
        'Create an Amazon Dynamo NoSQL database and add data to it. ' +
        'Then, connect and query that database from a Java Api using AWS Java SDK.',
      links: [
        'https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/SampleData.CreateTables.html',
        'https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-dynamodb.html',
      ],
    };
    const resource3: Resource = {
      name: 'Java REST Api using Spring Boot with Spring Kafka',
      category: 'Enterprise Software',
      description: 'Setup Kafka with your Java and Spring Boot application',
      links: [
        'https://dzone.com/articles/running-apache-kafka-on-windows-os',
        'https://www.youtube.com/watch?v=udnX21__SuU',
      ],
    };
    const resource4: Resource = {
      name: 'Socket Programming',
      category: 'Computer Networking',
      description: 'Setup TCP and UDP web sockets to learn alternative to HTTP communication in web apps.',
      links: ['https://flaviocopes.com/websockets/'],
    };

    this.resourceCollection = of([resource1, resource2, resource3, resource4]);
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
