import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Usuario {
  id: number;
  nombre: string;
  apellido: string;
  contraseña: string;
  correo: string;
  promedio: number;
  cantiCalif: number
}

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private baseUrl: string = 'http://localhost/api'; 

  constructor(private http: HttpClient) {}

  // Método para configurar la IP del servidor backend
  setIp(ip: string) {
    this.baseUrl = `http://${ip}/api`;
  }

  // Método para conseguir a los arrendadores
  getAllArrendadores() {
    return this.http.get<Usuario[]>(`${this.baseUrl}/get-arrendadores`);
  }

  // Método para conseguir a los arrendatarios
  getAllArrendatarios() {
    return this.http.get<Usuario[]>(`${this.baseUrl}/get-arrendatarios`);
  }
}
