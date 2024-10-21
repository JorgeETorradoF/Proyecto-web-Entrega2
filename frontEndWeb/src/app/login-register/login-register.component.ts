import { Component } from '@angular/core';
import { LoginService } from '../login.service';
import { RegistroService } from '../registro.service';
import { Router } from '@angular/router'; 
import { NavbarComponent } from '../Landing-Page/navbar/navbar.component';

@Component({
  selector: 'app-login-register',

  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.css'] // Asegúrate de que la ruta sea correcta
})
export class LoginRegisterComponent {
  isActive: boolean = false;

  constructor(
    private loginService: LoginService,
    private registroService: RegistroService,
    private router: Router // Inyectar Router para redirigir
  ) {}

  toggleRegister(): void {
    this.isActive = true; // Cambia a la vista de registro
  }

  toggleLogin(): void {
    this.isActive = false; // Cambia a la vista de inicio de sesión
  }

  onLoginSubmit(event: Event): void {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
  
    const email = (form[0] as HTMLInputElement).value; // Obtiene el correo
    const password = (form[1] as HTMLInputElement).value; // Obtiene la contraseña
  
    this.loginService.login({ correo: email, contraseña: password }).subscribe(
      (response) => {
        console.log('Inicio de sesión exitoso:', response);
        // Ahora accedemos a redirectUrl
        this.router.navigate([response.redirectUrl]); // Navegar a la URL recibida
      },
      (error) => {
        console.error('Error en el inicio de sesión:', error);
        // Manejar errores de inicio de sesión (mostrar mensaje al usuario)
      }
    );
  }
  

  onRegisterSubmit(event: Event): void {
    event.preventDefault();
    const form = event.target as HTMLFormElement;

    const name = (form[0] as HTMLInputElement).value; // Obtiene el nombre
    const lastName = (form[1] as HTMLInputElement).value; // Obtiene el apellido
    const email = (form[2] as HTMLInputElement).value; // Obtiene el correo
    const password = (form[3] as HTMLInputElement).value; // Obtiene la contraseña

    // Obtener el rol del input de radio
    const role = (form.elements.namedItem('role') as HTMLInputElement)?.value === 'arrendador'; // Obtiene el rol como booleano

    // Llama al servicio de registro
    this.registroService.register({ nombre: name, apellido: lastName, correo: email, contraseña: password, arrendador: role }).subscribe(
      (response) => {
        console.log('Registro exitoso:', response);
        // Aquí puedes redirigir al usuario o mostrar un mensaje de éxito
        role ? this.router.navigate([`/arrendador/${response.id}`]) : this.router.navigate([`/arrendatario/${response.id}`])
      },
      (error) => {
        console.error('Error en el registro:', error);
        // Manejar errores de registro (mostrar mensaje al usuario)
      }
    );
  }

}