import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../usuario.service';
import { CalificacionService } from '../calificacion.service';

interface Arrendatario {
  id: number;
  nombre: string;
  apellido: string;
  correo: string;
  promedio: number;
  cantiCalif: number;
  calificacion?: number; // Campo para la calificación que el usuario ingresará
  comentario?: string;   // Campo para el comentario que el usuario ingresará
}

@Component({
  selector: 'app-calificar-arrendatario',
  templateUrl: './calificar-arrendatario.component.html',
  styleUrls: ['./calificar-arrendatario.component.css'] // styleUrls corregido
})
export class CalificarArrendatarioComponent implements OnInit {
  arrendatariosPorCalificar: Arrendatario[] = []; // Lista de arrendatarios
  arrendatarioSeleccionado: Arrendatario | null = null; // arrendatario seleccionado para calificar

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private usuarioService: UsuarioService, // Servicio para manejar usuarios
    private calificacionService: CalificacionService // Servicio para manejar las calificaciones
  ) {}

  ngOnInit() {
    // Cargar los arrendatarios por calificar a través del servicio
    this.usuarioService.getAllArrendatarios().subscribe(
      (data: Arrendatario[]) => {
        this.arrendatariosPorCalificar = data;
      },
      (error) => {
        console.error('Error al cargar arrendatarios:', error);
      }
    );
  }

  calificar(arrendatario: Arrendatario) {
    this.arrendatarioSeleccionado = arrendatario; // Selecciona al arrendatario para calificar
  }

  enviarCalificacion() {
    if (this.arrendatarioSeleccionado && this.arrendatarioSeleccionado.calificacion) {
      const calificacionData = {
        idArrendatario: this.arrendatarioSeleccionado.id,
        calificacion: this.arrendatarioSeleccionado.calificacion,
        comentario: this.arrendatarioSeleccionado.comentario || ''
      };

      // Enviar la calificación a través del servicio
      this.calificacionService.calificarArrendatario(calificacionData).subscribe(
        (response) => {
          console.log('Calificación enviada exitosamente:', response);
          // Remover el arrendatario de la lista tras ser calificado
          this.arrendatariosPorCalificar = this.arrendatariosPorCalificar.filter(a => a.id !== this.arrendatarioSeleccionado!.id);
          this.arrendatarioSeleccionado = null; // Limpiar selección
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
