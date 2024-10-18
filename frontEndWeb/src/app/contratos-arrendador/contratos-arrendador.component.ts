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
  selector: 'app-contratos-arrendador',
  templateUrl: './contratos-arrendador.component.html',
  styleUrls: ['./contratos-arrendador.component.css']
})
export class ContratosArrendadorComponent implements OnInit {
  contratos: Contract[] = []; // Array para almacenar los contratos
  idArrendador!: number; // ID del arrendador
  constructor(private route: ActivatedRoute, private contratosService: ContratosService) {}

  ngOnInit() {

    // Obtener el ID del arrendador de la URL
    this.idArrendador = +this.route.snapshot.paramMap.get('idArrendador')!;
    console.log('ID del arrendador:', this.idArrendador);
    this.obtenerContratos();
  }

  // Método para obtener los contratos del backend usando el servicio
  obtenerContratos() {
    this.contratosService.getContratosArrendador(this.idArrendador).subscribe(
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

  
  // Método para aceptar un contrato
  aceptarContrato(id: number) {
    this.contratosService.aceptarContrato(this.idArrendador, id).subscribe(
      response => {
        console.log(`Contrato aceptado: ${id}`, response);
        this.obtenerContratos(); // Vuelve a cargar los contratos si es necesario
      },
      error => {
        console.error('Error al aceptar el contrato:', error);
      }
    );
  }

  // Método para rechazar un contrato
  rechazarContrato(id: number) {
    this.contratosService.rechazarContrato(this.idArrendador, id).subscribe(
      response => {
        console.log(`Contrato rechazado: ${id}`, response);
        this.obtenerContratos(); // Vuelve a cargar los contratos si es necesario
      },
      error => {
        console.error('Error al rechazar el contrato:', error);
      }
    );
  }
}
