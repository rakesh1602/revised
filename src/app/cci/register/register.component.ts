import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {
  AccountModel,
  Gender,
  PatientModel,
  PersonModel,
  Prefix,
  Role,
} from 'src/app/core/services/datamodel/PatientRegisteration';
import { RegisterService } from 'src/app/core/services/RegisterService';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  errorMessage?: string;
  person: PersonModel = new PersonModel();
  account: AccountModel = new AccountModel();
  patient: PatientModel = new PatientModel();
  passcode!: string;

  accountValue = {
    emailId: this.account.emailId,
  };

  personValue = {
    prefix: this.person.prefix,
    firstName: this.person.firstName,
    lastName: this.person.lastName,
    phoneNumber: this.person.phoneNumber,
    dob: this.person.dob,
    gender: this.person.gender,
    account: this.accountValue,
  };

  patientValue = {
    person: this.personValue,
  };

  prefix: { prefix: Prefix }[] = [];
  gender: { gender: Gender }[] = [];
  role: { role: Role }[] = [];

  rf = new PersonModel();
  ar = new AccountModel();

  constructor(private registerService: RegisterService) {}

  reactiveRegisterForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    emailId: new FormControl('', [Validators.required, Validators.email]),
    phoneNumber: new FormControl('', [
      Validators.required,
      Validators.maxLength(10),
      Validators.minLength(10),
    ]),
    dob: new FormControl('', Validators.required),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(8),
    ]),
  });

  get firstName(): FormControl {
    return this.reactiveRegisterForm.get('firstName') as FormControl;
  }

  get lastName(): FormControl {
    return this.reactiveRegisterForm.get('lastName') as FormControl;
  }

  get emailId(): FormControl {
    return this.reactiveRegisterForm.get('emailId') as FormControl;
  }

  get phoneNumber(): FormControl {
    return this.reactiveRegisterForm.get('phoneNumber') as FormControl;
  }

  get dob(): FormControl {
    return this.reactiveRegisterForm.get('dob') as FormControl;
  }

  get password(): FormControl {
    return this.reactiveRegisterForm.get('password') as FormControl;
  }



  ngOnInit(): void {
    this.personValue.phoneNumber?.toString;
    this.masterData();
  }

  

  masterData() {
    this.registerService.getPrefix().subscribe(
      (data) => {
        this.prefix = data;
      },
      (error) => {
        console.error(error);
      }
    );

    this.registerService.getGender().subscribe(
      (data) => {
        this.gender = data;
      },
      (error) => {
        console.error(error);
      }
    );

  }

  
  Register() {
    this.registerService.createPatient(this.patientValue).subscribe();
  }

  prefixFun(e: any) {
    this.personValue.prefix = e.target.value;
  }

  genderFun(e: any) {
    this.personValue.gender = e.target.value;
  }

  // roleFun(e:any){
  // this.userValue.role=e.target.value;
  // }
}
