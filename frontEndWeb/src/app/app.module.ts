import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Import FormsModule

import { AppComponent } from './app.component'; // Componente principal
import { ContratosArrendadorComponent } from './contratos-arrendador/contratos-arrendador.component'; // Componente de contratos
import { ContratosArrendatarioComponent } from './contratos-arrendatario/contratos-arrendatario.component';
import { PropiedadesArrendadorComponent } from './propiedades-arrendador/propiedades-arrendador.component';
import { CrearPropiedadComponent } from './propiedades-arrendador/crear-propiedad/crear-propiedad.component';
import { LoginRegisterComponent } from './login-register/login-register.component'; 

@NgModule({
  declarations: [
    AppComponent,                  // Componente raíz
    ContratosArrendadorComponent,  // Componente de contratos de arrendador
    ContratosArrendatarioComponent, // Componente de contratos de arrendatario
    CrearPropiedadComponent,        // componente de crear propiedades
    PropiedadesArrendadorComponent, //  componente de propiedades
    LoginRegisterComponent          //login y registro
  ],
  imports: [
    BrowserModule,                // Requerido para aplicaciones en el navegador
    AppRoutingModule,             // Configuración del enrutamiento
    HttpClientModule,             // Para manejar peticiones HTTP
    CommonModule,
    FormsModule,
    ReactiveFormsModule                   
  ],
  providers: [],
  bootstrap: [AppComponent]       // Componente raíz
})
export class AppModule { }
