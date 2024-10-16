import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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

@Injectable({
  providedIn: 'root'
})
export class PropiedadesService {
  private baseUrl: string = ''; // Inicialmente vacío

  constructor(private http: HttpClient) {}

  // Método para configurar la IP del servidor backend
  setIp(ip: string) {
    this.baseUrl = `http://${ip}/api`; // Define la base URL con la IP
  }

  // Obtener propiedades del arrendador
  getPropiedadesArrendador(idArrendador: number): Observable<Propiedad[]> {
    return this.http.get<Propiedad[]>(`${this.baseUrl}/arrendador/${idArrendador}/propiedades`);
  }

  // Crear nueva propiedad sin imagen
  crearPropiedad(idArrendador: number, propiedad: Propiedad): Observable<Propiedad> {
    return this.http.post<Propiedad>(`${this.baseUrl}/api/arrendador/${idArrendador}/registrar-propiedad`, propiedad);
  }

  // Crear nueva propiedad con imagen
  crearPropiedadConImagen(idArrendador: number, formData: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}/api/arrendador/${idArrendador}/registrar-propiedad`, formData);
  }

  // Editar propiedad existente
  editarPropiedad(idPropiedad: number, propiedad: Propiedad): Observable<Propiedad> {
    return this.http.put<Propiedad>(`${this.baseUrl}/arrendador/modificar-propiedad/${idPropiedad}`, propiedad);
  }

  // Eliminar propiedad
  eliminarPropiedad(idPropiedad: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/propiedades/${idPropiedad}`);
  }
}



