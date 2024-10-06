import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContratosService } from '../contratos.service'; // Importa el servicio

interface Contract {
  fechaInicio: string; 
  fechaFinal: string;   
  id: number;          
  idPropiedad: number; 
  idArrendatario: number; 
  estado: number;      
  precio: number;     
}

@Component({
  selector: 'app-contratos-arrendatario',
  templateUrl: './contratos-arrendatario.component.html',
  styleUrls: ['./contratos-arrendatario.component.css']
})
export class ContratosArrendatarioComponent implements OnInit {
  contratos: Contract[] = []; // Array para almacenar los contratos
  idArrendatario!: number; // ID del Arrendatario
  ip: string = 'localhost'; // se debe cambiar a la external ip de la máquina virtual si no es en vm dejemoslo localhost

  constructor(private route: ActivatedRoute, private contratosService: ContratosService) {}

  ngOnInit() {
    // Se configura la IP en el servicio
    this.contratosService.setIp(this.ip);

    // Obtener el ID del Arrendatario de la URL
    this.idArrendatario = +this.route.snapshot.paramMap.get('idArrendatario')!;
    console.log('ID del Arrendatario:', this.idArrendatario);
    this.obtenerContratos();
  }

  // Método para obtener los contratos del backend usando el servicio
  obtenerContratos() {
    this.contratosService.getContratosArrendatario(this.idArrendatario).subscribe(
      data => {
        console.log('Contratos obtenidos:', data); // Verifica la respuesta en la consola
        this.contratos = data; // Almacena los contratos en la propiedad
      },
      error => {
        console.error('Error al obtener contratos:', error); // imprime si llega a haber algún error
      }
    );
  }

  // Método para obtener el estado del contrato en formato legible
  getEstadoContrato(estado: number): string {
    switch (estado) {
      case 1:
        return 'Aceptado';
      case -1:
        return 'Rechazado';
      case 0:
        return 'Pendiente';
      default:
        return 'Desconocido'; // siempre estára entre los 3 primeros pero el compilador jode si no hay un default :'v
    }
  }
}