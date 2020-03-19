import { Component, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
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
    name: new FormControl('', [Validators.required]),
    category: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    links: new FormControl('', [Validators.required]),
  });

  /**
   * ng-add and ng-update checkbox values
   */
  ngAddChecked: false;
  ngUpdateChecked: false;

  @ViewChild('successPopup', { static: false }) successPopup;
  @ViewChild('failurePopup', { static: false }) failurePopup;
  dialogRef;
  statusUrl: string;

  constructor(private router: Router, public dialog: MatDialog) {}

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
      name: formValue.name,
      category: formValue.category,
      description: formValue.description,
      links: formValue.links,
    };

    try {
      // Add resource to database
      // this.resourceService.addResource(sub).then(subId => {
      //   this.statusUrl = subId;
      // });

      console.log('Resource created: ', sub);

      this.dialogRef = this.dialog.open(this.successPopup, {
        width: '600px',
      });
    } catch (error) {
      this.dialogRef = this.dialog.open(this.failurePopup, {
        width: '600px',
      });
    }

    this.routeToHome();
  }

  routeToHome(): void {
    this.router.navigate(['/']);
    this.dialogRef.close();
  }
}
