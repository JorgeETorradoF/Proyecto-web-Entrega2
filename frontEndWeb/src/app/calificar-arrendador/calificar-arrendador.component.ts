// import { Component, OnInit } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { FormsModule } from '@angular/forms';
// import { ContratosService } from '../contratos.service'; // Importar el servicio

// interface Solicitud {
//   id: number;
//   nombre: string;
//   fechaEntrega: Date;
//   calificacion?: number;
//   comentario?: string;
// }

// @Component({
//   selector: 'app-calificar-arrendador',
//   standalone: true,
//   imports: [CommonModule, FormsModule],
//   templateUrl: './calificar-arrendador.component.html',
//   styleUrls: ['./calificar-arrendador.component.css']
// })
// export class CalificarArrendadorComponent implements OnInit {
//   solicitudesPorCalificar: Solicitud[] = []; 
//   solicitudSeleccionada: Solicitud | null = null;

//   constructor(private contratosService: ContratosService) {}

//   ngOnInit() {
//     // Aquí llamamos al servicio para obtener los contratos que faltan por calificar
//     this.contratosService.getContratosArrendador(1).subscribe(
//       (contratos) => {
//         this.solicitudesPorCalificar = contratos.map(contrato => ({
//           id: contrato.id,
//           nombre: `Propiedad ID: ${contrato.idPropiedad}`,
//           fechaEntrega: new Date(contrato.fechaFinal),
//         }));
//       },
//       (error) => {
//         console.error('Error al obtener contratos por calificar:', error);
//       }
//     );
//   }

//   calificar(solicitud: Solicitud) {
//     this.solicitudSeleccionada = { ...solicitud, calificacion: 0, comentario: '' };
//   }

//   enviarCalificacion() {
//     if (this.solicitudSeleccionada) {
//       const calificacionData = {
//         idContrato: this.solicitudSeleccionada.id,
//         calificacion: this.solicitudSeleccionada.calificacion ?? 0,
//         comentario: this.solicitudSeleccionada.comentario ?? '',
//       };

//       // Llamar al servicio para enviar la calificación
//       this.contratosService.calificarArrendador(calificacionData).subscribe(
//         (response) => {
//           console.log('Calificación enviada exitosamente:', response);
//           // Remover la solicitud de la lista
//           this.solicitudesPorCalificar = this.solicitudesPorCalificar.filter(s => s.id !== this.solicitudSeleccionada!.id);
//           this.solicitudSeleccionada = null;
//           alert('Calificación enviada con éxito!');
//         },
//         (error) => {
//           console.error('Error al enviar la calificación:', error);
//           alert('Error al enviar la calificación.');
//         }
//       );
//     }
//   }
// }

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ContratosService } from '../contratos.service'; // Importar el servicio

interface Solicitud {
  id: number;
  nombre: string;
  fechaEntrega: Date;
  calificacion?: number;
  comentario?: string;
}

@Component({
  selector: 'app-calificar-arrendador',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './calificar-arrendador.component.html',
  styleUrls: ['./calificar-arrendador.component.css']
})
export class CalificarArrendadorComponent implements OnInit {
  solicitudesPorCalificar: Solicitud[] = []; // Aquí se llenará con la data del servicio o con ejemplos
  solicitudSeleccionada: Solicitud | null = null;

  constructor(private contratosService: ContratosService) {}

  ngOnInit() {
    // Ejemplo de contratos simulados antes de llamar al servicio
    this.solicitudesPorCalificar = [
      {
        id: 1,
        nombre: 'Contrato Finca El Paraíso',
        fechaEntrega: new Date(2024, 5, 15),
      },
      {
        id: 2,
        nombre: 'Contrato Hacienda Los Rosales',
        fechaEntrega: new Date(2024, 6, 20),
      },
      {
        id: 3,
        nombre: 'Contrato Cabaña del Lago',
        fechaEntrega: new Date(2024, 7, 5),
      }
    ];
  }

  calificar(solicitud: Solicitud) {
    this.solicitudSeleccionada = { ...solicitud, calificacion: 0, comentario: '' };
  }

  enviarCalificacion() {
    if (this.solicitudSeleccionada) {
      console.log('Calificación enviada:', this.solicitudSeleccionada);
      // Lógica para enviar la calificación
      const calificacionData = {
        idContrato: this.solicitudSeleccionada.id,
        calificacion: this.solicitudSeleccionada.calificacion || 0, // Asegúrate de que tenga valor
        comentario: this.solicitudSeleccionada.comentario || ''
      };

      // Llamar al servicio para enviar la calificación
      this.contratosService.calificarArrendador(calificacionData).subscribe(
        (response) => {
          console.log('Calificación enviada exitosamente:', response);
          // Remover la solicitud de la lista
          this.solicitudesPorCalificar = this.solicitudesPorCalificar.filter(s => s.id !== this.solicitudSeleccionada!.id);
          this.solicitudSeleccionada = null;
          alert('Calificación enviada con éxito!');
        },
        (error) => {
          console.error('Error al enviar la calificación:', error);
          alert('Error al enviar la calificación.');
        }
      );
    }
  }
}
