import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component'; // Componente principal
import { ContratosArrendadorComponent } from './contratos-arrendador/contratos-arrendador.component'; // Componente de contratos
import { LoginComponent } from './login/login.component'; // Componente de login

@NgModule({
  declarations: [
    AppComponent,                  // Componente raíz
    ContratosArrendadorComponent,  // Componente de contratos
  ],
  imports: [
    BrowserModule,                // Requerido para aplicaciones en el navegador
    AppRoutingModule,             // Configuración del enrutamiento
    HttpClientModule              // Para manejar peticiones HTTP
  ],
  providers: [],
  bootstrap: [AppComponent]       // Componente raíz
})
export class AppModule { }
