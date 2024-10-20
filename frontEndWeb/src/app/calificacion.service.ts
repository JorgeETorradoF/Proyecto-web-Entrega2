import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalificacionService {

  private baseUrl: string = 'http://localhost/api'; 

  constructor(private http: HttpClient) {}

  // Método para configurar la IP del servidor backend
  setIp(ip: string) {
    this.baseUrl = `http://${ip}/api`;
  }

  // Método para calificar al arrendador
  calificarArrendador(calificacionData: { idArrendador: number; calificacion: number }): Observable<any> {
    const url = `${this.baseUrl}/calificaciones/arrendador/${calificacionData.idArrendador}?calificacion=${calificacionData.calificacion}`;
    return this.http.post<any>(url, null); 
  }
  
  calificarArrendatario(calificacionData: { idArrendatario: number; calificacion: number }): Observable<any> {
    const url = `${this.baseUrl}/calificaciones/arrendatario/${calificacionData.idArrendatario}?calificacion=${calificacionData.calificacion}`;
    return this.http.post<any>(url, null);
  }
}
