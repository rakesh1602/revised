import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CategoriesComponent } from './category/categories/categories.component';
import { AllPostComponent } from './posts/all-post/all-post.component';
import { NewPostsComponent } from './posts/new-posts/new-posts.component';
import { LoginComponent } from './auth/login/login.component';
import { authGuard } from './services/auth.guard';

const routes: Routes = [
  {path: '', component: DashboardComponent, canActivate:[authGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'categories', component:CategoriesComponent, canActivate:[authGuard]},
  {path: 'posts', component:AllPostComponent, canActivate:[authGuard]},
  {path: 'posts/new', component: NewPostsComponent, canActivate:[authGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
