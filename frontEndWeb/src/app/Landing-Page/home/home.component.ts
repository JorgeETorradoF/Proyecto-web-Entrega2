import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private router: Router) {}  // Inyecta Router

  // Función para redirigir al login
  irAlLogin() {
    this.router.navigate(['/login']);  // Cambia la ruta si es necesario
  }
}
