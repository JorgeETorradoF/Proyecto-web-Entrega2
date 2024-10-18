import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Contract {
  fechaInicio: string; 
  fechaFinal: string;   
  id: number;          
  idPropiedad: number; 
  idArrendatario: number; 
  estado: number;      
  precio: number;     
}

@Injectable({
  providedIn: 'root'
})
export class ContratosService {
  private baseUrl: string = 'localhost'; // Inicialmente vacío

  constructor(private http: HttpClient) {}

  // Método para configurar la IP
  setIp(ip: string) {
    this.baseUrl = `http://${ip}/api`; // Se configura la url con base en la ip que se provee
  }

  // Método para obtener contratos por ID de arrendador
  getContratosArrendador(idArrendador: number): Observable<Contract[]> {
    return this.http.get<Contract[]>(`${this.baseUrl}/arrendador/${idArrendador}/mis-contratos`);
  }

  // Método para aceptar un contrato
  aceptarContrato(idArrendador: number, idContrato: number): Observable<any> {
    const url = `${this.baseUrl}/arrendador/${idArrendador}/aceptar-contrato/${idContrato}`;
    return this.http.put<any>(url, {});
  }

  // Método para rechazar un contrato
  rechazarContrato(idArrendador: number, idContrato: number): Observable<any> {
    const url = `${this.baseUrl}/arrendador/${idArrendador}/rechazar-contrato/${idContrato}`;
    return this.http.put<any>(url, {});
  }

  // Método para obtener contratos por ID de arrendatario
  getContratosArrendatario(idArrendatario: number): Observable<Contract[]> {
    return this.http.get<Contract[]>(`${this.baseUrl}/arrendatario/${idArrendatario}/mis-contratos`);
  }
}
