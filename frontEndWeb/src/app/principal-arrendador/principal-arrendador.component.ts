import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-principal-arrendador',
  templateUrl: './principal-arrendador.component.html',
  styleUrl: './principal-arrendador.component.css'
})
export class PrincipalArrendadorComponent implements OnInit {
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