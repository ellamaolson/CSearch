import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { ResourcesService, Resource } from '../resources.service';

@Component({
  selector: 'app-glossary',
  templateUrl: './glossary.component.html',
})
export class GlossaryComponent {
  /**
   * Get an observable array from the search service
   * containing all Resources from the search database
   */
  allResources: Observable<Resource[]> = this.resourceService.getResources();

  constructor(private resourceService: ResourcesService) {}
}
