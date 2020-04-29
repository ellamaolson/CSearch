import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ResourcesService, Resource } from '../resources.service';

@Component({
  selector: 'app-new-submission',
  templateUrl: './new-submission.component.html',
})
export class NewSubmissionComponent {
  /**
   * Form to enter all new submission input, with some validators
   * for required inputs.
   */
  submissionForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    process: new FormControl('', [Validators.required]),
    difficulty: new FormControl('', [Validators.required, Validators.min(1), Validators.max(5)]),
    links: new FormControl('', [Validators.required]),
  });

  constructor(private router: Router, private resourceService: ResourcesService) {}

  /**
   * Validate input, create a new submission, add it to the database, and notify of success.
   */
  onSubmit(): void {
    if (!this.submissionForm.valid) {
      alert('Please fill out all form boxes.');
      return;
    }

    const formValue = this.submissionForm.value;

    const sub: Resource = {
      title: formValue.title,
      description: formValue.description,
      process: formValue.process,
      difficulty: Number(formValue.difficulty),
      links: formValue.links,
    };

    try {
      this.resourceService.createResource(sub).subscribe();
    } catch (err) {
      alert('Looks like there was an error with the submission.');
    }

    alert('Thank you for your submission.');
    this.routeToHome();
  }

  routeToHome(): void {
    this.router.navigate(['/']);
  }
}
