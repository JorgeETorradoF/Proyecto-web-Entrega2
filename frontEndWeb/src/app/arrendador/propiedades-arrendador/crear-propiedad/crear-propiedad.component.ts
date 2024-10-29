import { Component, OnInit } from '@angular/core';
import { PropiedadesService } from '../../../services/propiedades.service';
import { ActivatedRoute, Router } from '@angular/router';
import departamentosData from '../../../../assets/colombia.json';

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
    tipoIngreso: '',
    cantidadHabitaciones: 1,
    cantidadBanos: 1,
    permiteMascotas: false,
    tienePiscina: false,
    tieneAsador: false,
    urlImagen: ''
  };

  departamentos: any[] = [];
  municipiosFiltrados: string[] = [];
  departamentoSeleccionado: string = '';

  idArrendador!: number;
  selectedFile: File | null = null;
  imagenSeleccionada: string | ArrayBuffer | null = null;

  constructor(
    private propiedadesService: PropiedadesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.propiedadesService.setIp('localhost');
    this.idArrendador = +this.route.snapshot.paramMap.get('idArrendador')!;
    this.departamentos = departamentosData;
  }

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

  // Método para cargar municipios desde el JSON de assets
    cargarMunicipios(): void {
    const departamento = this.departamentos.find(
      (d) => d.departamento.trim().toLowerCase() === this.departamentoSeleccionado.trim().toLowerCase()
    );

    if (departamento) {
      this.municipiosFiltrados = departamento.ciudades;
      console.log('Municipios filtrados:', this.municipiosFiltrados);
      this.propiedad.departamento = this.departamentoSeleccionado; // Asigna el departamento seleccionado a la propiedad
    } else {
      console.warn('No se encontró el departamento seleccionado.');
      this.municipiosFiltrados = [];
    }
  }

  // Método para crear una propiedad nueva
  crearPropiedad() {
    if (
      this.propiedad.nombrePropiedad === '' ||
      this.propiedad.departamento === '' ||
      this.propiedad.municipio === '' ||
      this.propiedad.tipoIngreso === ''
    ) {
      console.error('Error: Algunos campos requeridos están vacíos');
      return;
    }

    // Si no se selecciona la imagen
    if (!this.selectedFile) {
      console.error('Error: No se ha seleccionado ninguna imagen');
      return;
    }

    // Crear FormData para enviar la imagen junto con los datos de la propiedad
    const formData = new FormData();
    formData.append('imagen', this.selectedFile);

    this.propiedad.permiteMascotas = this.propiedad.permiteMascotas || false;
    this.propiedad.tienePiscina = this.propiedad.tienePiscina || false;
    this.propiedad.tieneAsador = this.propiedad.tieneAsador || false;

    // Crea el objeto propiedadDTO y lo agrega a formData
    const propiedadDTO = {
      nombrePropiedad: this.propiedad.nombrePropiedad.trim(),
      descripcion: this.propiedad.descripcion ? this.propiedad.descripcion.trim() : '',
      valorNoche: this.propiedad.valorNoche ? this.propiedad.valorNoche.toString() : '0',
      departamento: this.propiedad.departamento.trim(),
      municipio: this.propiedad.municipio.trim(),
      tipoIngreso: this.propiedad.tipoIngreso.trim(),
      cantidadHabitaciones: this.propiedad.cantidadHabitaciones ? this.propiedad.cantidadHabitaciones.toString() : '1',
      cantidadBanos: this.propiedad.cantidadBanos ? this.propiedad.cantidadBanos.toString() : '0',
      permiteMascotas: this.propiedad.permiteMascotas ? this.propiedad.permiteMascotas.toString() : 'false',
      tienePiscina: this.propiedad.tienePiscina ? this.propiedad.tienePiscina.toString() : 'false',
      tieneAsador: this.propiedad.tieneAsador ? this.propiedad.tieneAsador.toString() : 'false',
    };

    formData.append('propiedadDTO', new Blob([JSON.stringify(propiedadDTO)], { type: "application/json" }));

    // Llama al servicio para crear la propiedad con la imagen y los datos
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
  }

  onSubmit() {
    this.crearPropiedad();
  }

  cambiarImagen() {
    this.imagenSeleccionada = null;
    this.selectedFile = null;
  }
}
