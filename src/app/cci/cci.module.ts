import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MaterialModule } from '../material-module/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { PatientComponent } from './patient/patient.component';



@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    PatientComponent,
    
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule
    
  ],
  exports: [
    LoginComponent,
    RegisterComponent
  ]
})
export class CciModule { }
