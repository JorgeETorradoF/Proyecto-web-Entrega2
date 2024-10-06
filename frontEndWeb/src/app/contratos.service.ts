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
  private baseUrl: string = ''; // Inicialmente vacío

  constructor(private http: HttpClient) {}

  // Método para configurar la IP
  setIp(ip: string) {
    this.baseUrl = `http://${ip}/api`; // Establece la base URL con la IP proporcionada
  }

  // Método para obtener contratos por ID de arrendador
  getContratosArrendador(idArrendador: number): Observable<Contract[]> {
    return this.http.get<Contract[]>(`${this.baseUrl}/arrendador/${idArrendador}/mis-contratos`);
  }

  // Método para obtener contratos por ID de arrendatario
  getContratosArrendatario(idArrendatario: number): Observable<Contract[]> {
    return this.http.get<Contract[]>(`${this.baseUrl}/arrendatario/${idArrendatario}/mis-contratos`);
  }
}
