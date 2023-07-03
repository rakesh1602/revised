import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './cci/login/login.component';
import { RegisterComponent } from './cci/register/register.component';


const routes: Routes = [
  
  {
    path: 'enrollments/:id',
    component: LoginComponent,
  },

  {
    path: 'registers',
    component: RegisterComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
