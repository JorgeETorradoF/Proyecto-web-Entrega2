import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-principal-arrendatario',
  standalone: true,
  imports: [],
  templateUrl: './principal-arrendatario.component.html',
  styleUrl: './principal-arrendatario.component.css'
})
export class PrincipalArrendatarioComponent implements OnInit {
  idArrendatario!: string;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.idArrendatario = this.route.snapshot.paramMap.get('idArrendatario')!;
  }

  navigateToVerContratos() {
    this.router.navigate([`/arrendatario/${this.idArrendatario}/contratos`]);
  }

  navigateToCalificar() {
    this.router.navigate([`/arrendatario/${this.idArrendatario}/calificar`]);
  }
}

