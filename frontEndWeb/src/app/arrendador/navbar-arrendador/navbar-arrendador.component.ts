import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-navbar-arrendador',
  templateUrl: './navbar-arrendador.component.html',
  styleUrl: './navbar-arrendador.component.css'
})
export class NavbarArrendadorComponent implements OnInit {
  idArrendador!: string;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.idArrendador = this.route.snapshot.paramMap.get('idArrendador')!;
  }

  navigateToCalificar() {
    this.router.navigate([`/arrendador/${this.idArrendador}/calificar`]);
  }

  navigateToVerContratos() {
    this.router.navigate([`/arrendador/${this.idArrendador}/contratos`]);
  }

  navigateToVerPropiedades() {
    this.router.navigate([`/arrendador/${this.idArrendador}/propiedades`]);
  }

}
