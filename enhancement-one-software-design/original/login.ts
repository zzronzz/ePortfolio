import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { User } from '../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule], // enables ngModel for form binding
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {

  // Holds user input for login
  credentials: User = new User();
  password: string = '';

  constructor(
    private authService: AuthenticationService, // handles authentication logic
    private router: Router                      // used for navigation after login
  ) {}

  // Called when login form is submitted
  onSubmit(): void {
    this.authService.login(this.credentials, this.password)
      .subscribe({
        next: (res: any) => {
          // Save JWT token to localStorage
          this.authService.saveToken(res.token);

          // Redirect to home page after successful login
          this.router.navigate(['']);
        },
        error: (err) => {
          // Log any login errors
          console.log(err);
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