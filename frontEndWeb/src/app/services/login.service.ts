import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface LoginResponse {
  redirectUrl: string; // Define el tipo de respuesta
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private ip: string = 'localhost'; // IP por defecto

  constructor(private http: HttpClient) {}

  setIp(ip: string): void {
    this.ip = ip;
  }

  login(data: { correo: string; contraseña: string }): Observable<LoginResponse> {
    const url = `http://${this.ip}/api/iniciar-sesion`;
    return this.http.post<LoginResponse>(url, data); // Cambia el tipo de retorno a LoginResponse
  }
}

