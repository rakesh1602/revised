import { HttpClient } from '@angular/common/http';

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';

import { RegisterService } from 'src/app/core/services/RegisterService';
import { Credentials } from 'src/app/core/services/datamodel/PatientRegisteration';

@Component({
  selector: 'app-login',

  templateUrl: './login.component.html',

  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  enrollmentId!: any | null;
  // data!: Credentials;
  // confirmPass!: string;

  // credentials:Credentials= {
  //   emailId: this.data.emailId,
  //   dob: this.data.dob,
  //   password: this.data.password,
  // };

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private registerService: RegisterService
  ) {}

  ngOnInit() {
    this.authenticationPerson();
  }

  authorization(guid: any) {
    this.router.navigate(['enrollments', guid]);
  }

  authenticationPerson() {
    this.route.paramMap.subscribe((params) => {
      this.enrollmentId = params.get('id');
      console.log(this.enrollmentId);

      this.registerService.enrollment(this.enrollmentId).subscribe(
        (response: any) => {
          if (this.enrollmentId === response.enrollmentID) {
            this.authorization(response.enrollmentID);
          } else {
            this.router.navigateByUrl('/registers');
            alert('Invalid URL');
          }
        },
        (error) => {
          console.log('Error retrieving enrollment data');
          this.router.navigateByUrl('/registers');
          alert('Invalid URL');
        }
      );
    });
  }

  // createPassword = new FormGroup({
  //   emailId: new FormControl('', [Validators.required, Validators.email]),
  //   dob: new FormControl('', Validators.required),
  //   password: new FormControl('', [Validators.required, Validators.minLength(8),]),
  // });

  // onSubmit() {
  //   console.log('Form is Submitted');

  //   console.log(
  //     this.credentials.dob,
  //     this.credentials.email,
  //     this.credentials.password
  //   );

  //   if (this.confirmPass === this.credentials.password) {
  //     this.registerService.createPassword(this.credentials).subscribe();
  //   }
  // }
}
