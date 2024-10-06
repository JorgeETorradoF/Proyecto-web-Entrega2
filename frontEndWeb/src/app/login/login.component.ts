import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  title: string = 'Iniciar Sesión';
  isActive: boolean = false;

  toggleRegister(): void {
    this.isActive = true;
  }

  toggleLogin(): void {
    this.isActive = false;
  }
}
