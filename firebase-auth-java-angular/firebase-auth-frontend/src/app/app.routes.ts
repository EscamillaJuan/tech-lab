import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { authGuard } from './guards/auth-guard';
import { Register } from './components/register/register';
import { canActivate } from '@angular/fire/auth-guard';
import { Home } from './components/home/home';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: Login },
  { path: 'register', component: Register},
  { path: 'home', canActivate: [authGuard], component: Home}
];
