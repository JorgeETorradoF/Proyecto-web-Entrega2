import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {
  private ip: string = 'localhost'; // IP por defecto

  constructor(private http: HttpClient) {}

  setIp(ip: string): void {
    this.ip = ip;
  }

  register(data: { nombre: string; apellido: string; correo: string; contraseña: string; arrendador: boolean }): Observable<any> {
    const url = `http://${this.ip}/api/crear-cuenta`;
    return this.http.post(url, data);
  }
}
