import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-navbar-arrendatario',
  templateUrl: './navbar-arrendatario.component.html',
  styleUrl: './navbar-arrendatario.component.css'
})

export class NavbarArrendatarioComponent implements OnInit{

  idArrendatario!: number;  // Se obtiene de la URL
  ip: string = 'localhost'; // IP del servidor backend

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    // Obtiene el id del arrendador desde la ruta (URL)
    this.idArrendatario = +this.route.snapshot.paramMap.get('idArrendatario')!;// Llamamos para obtener las propiedades del arrendador
  }
    // Métodos de navegación a otras vistas
    navigateToVerContratos() {
      this.router.navigate([`/arrendatario/${this.idArrendatario}/contratos`]);
    }
  
    navigateToCalificar() {
      this.router.navigate([`/arrendatario/${this.idArrendatario}/calificar`]);
    }  
  
    navigateToPrincipal()
    {
      this.router.navigate([`/arrendatario/${this.idArrendatario}`]);
    }
}
