import { Component, OnInit } from '@angular/core';
import { PropiedadesService } from '../../propiedades.service';
import { ActivatedRoute, Router } from '@angular/router';

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
  urlImagen: string; // Para la imagen pequeña descriptiva
}

@Component({
  selector: 'app-crear-propiedad',
  templateUrl: './crear-propiedad.component.html',
  styleUrls: ['./crear-propiedad.component.css']
})
export class CrearPropiedadComponent implements OnInit {
  propiedad: Propiedad = {
    id: 0,
    nombrePropiedad: '',
    descripcion: '',
    valorNoche: 0,
    departamento: '',
    municipio: '',
    tipoIngreso: '', // Campo obligatorio para tipo de ingreso
    cantidadHabitaciones: 1, // Inicializa con un valor mínimo
    cantidadBanos: 1, // Inicializa con un valor mínimo
    permiteMascotas: false,
    tienePiscina: false,
    tieneAsador: false,
    urlImagen: '' // Campo de imagen predeterminado
  };

  idArrendador!: number; // ID del arrendador, se obtiene de la URL
  selectedFile: File | null = null; // Variable para almacenar el archivo seleccionado
  imagenSeleccionada: string | ArrayBuffer | null = null; // Para mostrar la vista previa de la imagen seleccionada

  constructor(
    private propiedadesService: PropiedadesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    // Obtener el ID del arrendador desde la URL
    this.idArrendador = +this.route.snapshot.paramMap.get('idArrendador')!;
  }

  // Método para seleccionar el archivo
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.onload = () => {
        this.imagenSeleccionada = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
    console.log('Archivo seleccionado:', this.selectedFile);
  }

  // Método para crear una propiedad nueva
  crearPropiedad() {
    // Validaciones simples
    if (
      this.propiedad.nombrePropiedad === '' ||
      this.propiedad.departamento === '' ||
      this.propiedad.municipio === '' ||
      this.propiedad.tipoIngreso === ''
    ) {
      console.error('Error: Algunos campos requeridos están vacíos');
      return;
    }

    // Crear FormData para enviar la imagen junto con los datos de la propiedad
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('imagen', this.selectedFile); // Subimos la imagen
      formData.append('nombrePropiedad', this.propiedad.nombrePropiedad);
      formData.append('descripcion', this.propiedad.descripcion);
      formData.append('valorNoche', this.propiedad.valorNoche.toString());
      formData.append('departamento', this.propiedad.departamento);
      formData.append('municipio', this.propiedad.municipio);
      formData.append('tipoIngreso', this.propiedad.tipoIngreso);
      formData.append('cantidadHabitaciones', this.propiedad.cantidadHabitaciones.toString());
      formData.append('cantidadBanos', this.propiedad.cantidadBanos.toString());
      formData.append('permiteMascotas', this.propiedad.permiteMascotas.toString());
      formData.append('tienePiscina', this.propiedad.tienePiscina.toString());
      formData.append('tieneAsador', this.propiedad.tieneAsador.toString());

      // Llama al servicio para crear la propiedad con la imagen
      this.propiedadesService.crearPropiedadConImagen(this.idArrendador, formData).subscribe(
        data => {
          console.log('Propiedad creada:', data);
          // Redirigir a la lista de propiedades después de crearla
          this.router.navigate([`/arrendador/${this.idArrendador}/propiedades`]);
        },
        error => {
          console.error('Error al crear propiedad:', error);
        }
      );
    } else {
      console.error('Error: No se ha seleccionado ninguna imagen');
    }
  }

  // Método que se invocará al enviar el formulario
  onSubmit() {
    this.crearPropiedad(); // Invoca el método para crear la propiedad
  }

  // Método para cambiar la imagen seleccionada
  cambiarImagen() {
    this.imagenSeleccionada = null;
    this.selectedFile = null; // Permite al usuario cambiar la imagen seleccionada
  }
}
