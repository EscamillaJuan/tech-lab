import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Auth, createUserWithEmailAndPassword, user } from '@angular/fire/auth';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  private router = inject(Router);
  private auth = inject(Auth)

  user$ = user(this.auth)
  email: string = '';
  password: string = '';
  name: string = '';

  constructor() {
    this.user$.subscribe(u => {
      if (u) {
        this.router.navigate(['/home']);
      }
    });
  }

  async register() {
    try {
      await createUserWithEmailAndPassword(this.auth, this.email, this.password);
      this.router.navigate(['/home']);
    } catch (error) {
      console.error('Registration failed', error);
    }
  }

  login() {
    this.router.navigate(['/login'])
  }
}
