import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {

  // Holds user input for login
  credentials: User = new User();
  password: string = '';

  // User-facing feedback message
  errorMessage: string = '';

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {}

  // Checks for missing or blank values
  private isBlank(value: any): boolean {
    return value === undefined || value === null || String(value).trim() === '';
  }

  // Called when login form is submitted
  onSubmit(): void {
    this.errorMessage = '';

    if (this.isBlank(this.credentials.email) || this.isBlank(this.password)) {
      this.errorMessage = 'Please enter both email and password.';
      return;
    }

    this.authService.login(this.credentials, this.password)
      .subscribe({
        next: (res: any) => {
          // Save JWT token to localStorage
          this.authService.saveToken(res.token);

          // Redirect to home page after successful login
          this.router.navigate(['']);
        },
        error: (err) => {
          // Log error for debugging and show safe message to user
          console.log(err);
          this.errorMessage = 'Login failed. Please check your email and password.';
          alert(this.errorMessage);
        }
      });
  }

  // Prevent access to login page if already logged in
  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['']);
    }
  }
}