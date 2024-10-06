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
  ip: string = 'localhost'; // Cambia esto a la IP deseada o configúralo dinámicamente si es necesario

  constructor(private route: ActivatedRoute, private contratosService: ContratosService) {}

  ngOnInit() {
    // Configura la IP en el servicio
    this.contratosService.setIp(this.ip);

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
        console.error('Error al obtener contratos:', error); // Verifica si hay un error
      }
    );
  }

  // Método para aceptar un contrato
  aceptarContrato(id: number) {
    console.log(`Contrato aceptado: ${id}`);
    // Aquí podrías agregar la lógica para aceptar el contrato, posiblemente haciendo una petición al backend
  }

  // Método para rechazar un contrato
  rechazarContrato(id: number) {
    console.log(`Contrato rechazado: ${id}`);
    // Aquí podrías agregar la lógica para rechazar el contrato, posiblemente haciendo una petición al backend
  }
}
