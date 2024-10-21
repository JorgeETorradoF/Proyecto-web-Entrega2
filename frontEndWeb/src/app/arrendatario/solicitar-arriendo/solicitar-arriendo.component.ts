import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PropiedadesService } from '../../services/propiedades.service'; // Importamos el servicio

interface Propiedad {
  id: number;
  nombrePropiedad: string;
  descripcion: string;
  valorNoche: number;
  departamento: string;
  municipio: string;
  tipoIngreso: string;
  cantidadHabitaciones: number;
  cantidadBanos: number;
  permiteMascotas: boolean;
  tienePiscina: boolean;
  tieneAsador: boolean;
  urlImagen: string;
}

@Component({
  selector: 'app-solicitar-arriendo',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './solicitar-arriendo.component.html',
  styleUrls: ['./solicitar-arriendo.component.css']
})
export class SolicitarArriendoComponent implements OnInit {
  propiedad: Propiedad | undefined; // La propiedad que será obtenida del servicio
  solicitud = {
    fechaInicial: '',
    fechaFinal: '',
    cantidadPersonas: 0,
  };

  constructor(
    private route: ActivatedRoute,
    private propiedadesService: PropiedadesService // Inyectamos el servicio
  ) {}

  ngOnInit() {
    const propiedadId = this.route.snapshot.paramMap.get('id'); // Obtenemos el ID de la propiedad de la ruta
    const idArrendador = this.route.snapshot.paramMap.get('idArrendador'); // Obtenemos el ID del arrendador

    if (propiedadId && idArrendador) {
      // Llamamos al servicio para obtener la propiedad específica
      this.propiedadesService.getPropiedad(parseInt(idArrendador), parseInt(propiedadId)).subscribe(
        (data: Propiedad) => {
          this.propiedad = data; // Asignamos los datos de la propiedad obtenida
        },
        (error) => {
          console.error('Error al obtener la propiedad:', error);
        }
      );
    }
  }

  onSubmit() {
    console.log('Solicitud enviada:', this.solicitud);
    alert('Solicitud de arriendo enviada con éxito!');
  }
}
