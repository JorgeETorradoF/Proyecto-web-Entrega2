import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private router: Router) {}  // Inyecta Router

  // Función para redirigir al login
  irAlLogin() {
    this.router.navigate(['/login']);  // Cambia la ruta si es necesario
  }
}
