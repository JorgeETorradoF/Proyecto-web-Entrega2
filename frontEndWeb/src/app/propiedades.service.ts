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
  private baseUrl: string = 'http://localhost/api'; // Inicialmente vacío

  constructor(private http: HttpClient) {}

  // Método para configurar la IP del servidor backend
  setIp(ip: string) {
    this.baseUrl = `http://${ip}/api`;
  }

  // Obtener propiedades del arrendador
  getPropiedadesArrendador(idArrendador: number): Observable<Propiedad[]> {
    return this.http.get<Propiedad[]>(`${this.baseUrl}/arrendador/${idArrendador}/propiedades`);
  }

  // Crear nueva propiedad sin imagen
  crearPropiedad(idArrendador: number, propiedad: Propiedad): Observable<Propiedad> {
    return this.http.post<Propiedad>(`${this.baseUrl}/arrendador/${idArrendador}/registrar-propiedad`, propiedad);
  }

  crearPropiedadConImagen(idArrendador: number, formData: FormData): Observable<any> {
    console.log('Base URL:', this.baseUrl);
    const url = `${this.baseUrl}/arrendador/${idArrendador}/registrar-propiedad`;
    console.log('POST URL:', url);
    return this.http.post(url, formData);
  }

  // Obtener los detalles de una propiedad específica
  getPropiedad(idArrendador: number, idPropiedad: number): Observable<Propiedad> {
    return this.http.get<Propiedad>(
      `${this.baseUrl}/arrendador/${idArrendador}/propiedad/${idPropiedad}`,
      { responseType: 'json' }
    );
  }


  editarPropiedad(idArrendador: number, idPropiedad: number, propiedad: any): Observable<any> {
    const url = `${this.baseUrl}/arrendador/${idArrendador}/modificar-propiedad/${idPropiedad}`;
    return this.http.put(url, propiedad, { headers: { 'Content-Type': 'application/json' } });
  }


  // Eliminar propiedad
  eliminarPropiedad(idPropiedad: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/propiedades/${idPropiedad}`);
  }
  // propiedades.service.ts
  obtenerPropiedad(idArrendador: number, idPropiedad: number) {
  const url = `${this.baseUrl}/arrendador/${idArrendador}/propiedad/${idPropiedad}`;
  return this.http.get(url);
  }

}



