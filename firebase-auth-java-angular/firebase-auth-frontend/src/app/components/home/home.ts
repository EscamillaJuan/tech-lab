import { Component, inject, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { signOut } from 'firebase/auth';
import { Auth } from '@angular/fire/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class Home implements OnInit {
  content: string = '';
  loading: boolean = true;
  error: string | null = null;
  private auth = inject(Auth);
  private router = inject(Router);


  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchHomeContent();
  }

  fetchHomeContent(): void {
    this.http.get<{ message: string }>('http://localhost:8080/api/v1')
      .subscribe({
        next: (response) => {
          this.content = response.message;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'Failed to load dashboard content.';
          this.loading = false;
          console.error(err);
        }
      });
  }

  async logout() {
    await signOut(this.auth);
    this.router.navigate(["/login"])
  }
}
