import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false)
  isLoggedInGuard: boolean =false
  constructor(private auth: AngularFireAuth, private toaster: ToastrService, private route: Router) { }

  login(email: string, password: string){
    this.auth.signInWithEmailAndPassword(email, password).then(()=>{
      this.toaster.success("Logged in successfully.");
      this.loadUser();
      this.loggedIn.next(true);
      this.isLoggedInGuard = true
      this.route.navigate(['/'])
    }).catch(e=>{
      this.toaster.warning(e);
    })
  }

  loadUser(){
    this.auth.authState.subscribe(user=>{
      console.log(JSON.parse(JSON.stringify(user)));
      localStorage.setItem('user', JSON.stringify(user))
    })
  }

  logOut(){
    this.auth.signOut().then(()=>{
      this.toaster.warning("User logged out successfully");
      localStorage.removeItem('user')
      this.loggedIn.next(false)
      this.isLoggedInGuard = false
      this.route.navigate(['/login'])
    })
  }

  isLoggedIn(){
    return this.loggedIn.asObservable();
  }
}
