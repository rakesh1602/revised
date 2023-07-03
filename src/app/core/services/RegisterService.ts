import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credentials, Gender, Prefix } from './datamodel/PatientRegisteration';

const BASE_URL = 'http://localhost:8080';
const API_URL = BASE_URL + '/persons';
const PATIENT_REGISTER_URL = BASE_URL + '/patients';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  constructor(private http: HttpClient) {}

  createPatient(patientModel: {
    person: {
      prefix: string | undefined;
      firstName: string | undefined;
      lastName: string | undefined;
      phoneNumber: string | undefined;
      dob: Date | undefined;
      gender: string | undefined;

      account: {
        emailId: string | undefined;
      };
    };
  }): Observable<object> {
    return this.http.post<object>(PATIENT_REGISTER_URL, patientModel);
  }

  enrollment(enrollmentId: any): Observable<string> {
    return this.http.get<any>(
      `http://localhost:8080/enrollments/${enrollmentId}`
    );
  }

  getPrefix(): Observable<{ prefix: Prefix }[]> {
    return this.http.get<{ prefix: Prefix }[]>(BASE_URL + '/prefixs');
  }

  getGender(): Observable<{ gender: Gender }[]> {
    return this.http.get<{ gender: Gender }[]>(BASE_URL + '/genders');
  }

  // createPassword(credentials: Credentials): Observable<object> {
  //   return this.http.post<object>(BASE_URL + '/password', credentials);
  // }
}
