import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Para redirigir a otras rutas
import { HttpClient } from '@angular/common/http'; // Para hacer peticiones HTTP
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  title: string = 'Iniciar Sesión';
  isActive: boolean = false;
  loginData = { email: '', password: '' };
  registerData = { nombre: '', apellido: '', email: '', password: '', role: '' };

  constructor(private http: HttpClient, private router: Router) {}

  toggleRegister(): void {
    this.isActive = true;
  }

  toggleLogin(): void {
    this.isActive = false;
  }

  // Método para enviar los datos de inicio de sesión
  onLoginSubmit(): void {
    this.http.post('http://localhost:8080/api/login', this.loginData)
      .subscribe(
        (response) => {
          // Redirigir después del login exitoso
          this.router.navigate(['/dashboard']);
        },
        (error) => {
          console.error('Error en el login', error);
        }
      );
  }

  // Método para enviar los datos de registro
  onRegisterSubmit(): void {
    this.http.post('http://localhost:8080/api/register', this.registerData)
      .subscribe(
        (response) => {
          // Redirigir después del registro exitoso
          this.router.navigate(['/welcome']);
        },
        (error) => {
          console.error('Error en el registro', error);
        }
      );
  }
}
