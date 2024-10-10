import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // Asegúrate de importar CommonModule

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule], // Importa CommonModule aquí
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class logInComponent {
  title: String = 'log in'
  isActive: boolean = false;

  // Cuando el usuario hace click en "Registrarse"
  toggleRegister(): void {
    this.isActive = true; // Activa el formulario de registro
  }

  // Cuando el usuario hace click en "Iniciar Sesión"
  toggleLogin(): void {
    this.isActive = false; // Activa el formulario de login
  }
}
