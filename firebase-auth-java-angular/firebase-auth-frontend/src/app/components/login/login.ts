import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Auth, signInWithEmailAndPassword, signOut, user } from '@angular/fire/auth';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  private auth = inject(Auth);
  private router = inject(Router);

  user$ = user(this.auth);
  email: string = '';
  password: string = '';

  constructor() {
    this.user$.subscribe(u => {
      if (u) {
        this.router.navigate(['/home']);
      }
    });
  }

  async login() {
    try {
      await signInWithEmailAndPassword(this.auth, this.email, this.password);
    } catch (error) {
      console.error('Login failed', error);
    }
  }

  async logout() {
    try {
      await signOut(this.auth);
      this.router.navigate(['/login']);
    } catch (error) {
      console.error('Logout failed', error);
    }
  }

  signUp() {
    this.router.navigate(['/register'])
  }
}
