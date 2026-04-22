import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  message: string = '';
  isSuccess: boolean = false;

  login(username: string, password: string) {

    // reset message
    this.message = '';

    if (!username || !password) {
      this.message = 'Please enter username and password';
      this.isSuccess = false;
      return;
    }

    // 🔐 Demo credentials
    if (username === 'admin' && password === '12345') {
      this.message = 'Login successful! Welcome back 🎉';
      this.isSuccess = true;

      // this.router.navigate(['/dashboard']);

    } else {
      this.message = 'Invalid credentials ❌';
      this.isSuccess = false;
    }
    alert(this.message);
    console.log(username, password);
  }
}
