import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router)
  const toaster = inject(ToastrService)
  
  if(authService.isLoggedInGuard){
    console.log("Access given "); 
    return true;
  } else{
    toaster.warning("You don't have permission to access this page.")
    router.navigate(['/login'])
    return false;
  }
};


