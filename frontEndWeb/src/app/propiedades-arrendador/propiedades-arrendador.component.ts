import { Component, OnInit } from '@angular/core';
import { PropiedadesService } from '../propiedades.service';
import { ActivatedRoute, Router } from '@angular/router';

interface Propiedad {
  id: number;
  nombrePropiedad: string;
  descripcion: string;
  valorNoche: number;
  departamento: string;
  municipio: string;
  urlImagen: string; // Para la imagen
}

@Component({
  selector: 'app-propiedades-arrendador',
  templateUrl: './propiedades-arrendador.component.html',
  styleUrls: ['./propiedades-arrendador.component.css']
})
export class PropiedadesArrendadorComponent implements OnInit {
  propiedades: Propiedad[] = [];
  idArrendador!: number;  // Se obtiene de la URL
  ip: string = 'localhost'; // IP del servidor backend

  constructor(
    private propiedadesService: PropiedadesService,
    private route: ActivatedRoute,
    private router: Router // Inyecta el servicio Router
  ) {}

  ngOnInit() {
    // Configura la IP del backend para las solicitudes
    this.propiedadesService.setIp(this.ip);

    // Obtiene el id del arrendador desde la ruta (URL)
    this.idArrendador = +this.route.snapshot.paramMap.get('idArrendador')!;
    this.obtenerPropiedades();
  }

  // Método para obtener las propiedades del arrendador desde el servicio
  obtenerPropiedades() {
    this.propiedadesService.getPropiedadesArrendador(this.idArrendador).subscribe(
      data => {
        console.log('Propiedades obtenidas:', data);
        this.propiedades = data; // Asigna las propiedades obtenidas a la variable
      },
      error => {
        console.error('Error al obtener propiedades:', error); // Muestra un mensaje de error si algo sale mal
      }
    );
  }

  // Método para agregar una nueva propiedad (redirige a la pantalla de creación)
  agregarPropiedad() {
    this.router.navigate([`/arrendador/${this.idArrendador}/crear-propiedad`]); // Navega a la vista de creación de propiedad
  }

  // Método para redirigir a la pantalla de edición de propiedad
  editarPropiedad(idPropiedad: number) {
    this.router.navigate([`/arrendador/editar-propiedad/${idPropiedad}`]); // Navega a la vista de edición
  }

  // Método para ver el detalle de una propiedad
  verDetallePropiedad(idPropiedad: number) {
    this.router.navigate([`/arrendador/detalle-propiedad/${idPropiedad}`]); // Navega a la vista de detalles
  }
}
