import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { ResultsListComponent } from './results-list/results-list.component';
import { GlossaryComponent } from './glossary/glossary.component';
import { AboutComponent } from './about/about.component';
import { NewSubmissionComponent } from './new-submission/new-submission.component';

const routes: Routes = [
  { path: '', component: SearchBarComponent },
  { path: 'results', component: ResultsListComponent },
  { path: 'results/:searchTerm', component: ResultsListComponent },
  { path: 'glossary', component: GlossaryComponent },
  { path: 'about', component: AboutComponent },
  { path: 'new-submission', component: NewSubmissionComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
