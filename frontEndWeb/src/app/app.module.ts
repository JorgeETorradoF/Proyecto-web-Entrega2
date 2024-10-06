import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

import { AppComponent } from './app.component'; // Componente principal
import { ContratosArrendadorComponent } from './contratos-arrendador/contratos-arrendador.component'; // Componente de contratos
import { ContratosArrendatarioComponent } from './contratos-arrendatario/contratos-arrendatario.component';

@NgModule({
  declarations: [
    AppComponent,                  // Componente raíz
    ContratosArrendadorComponent,  // Componente de contratos de arrendador
    ContratosArrendatarioComponent //componente de contratos de arrendatario
  ],
  imports: [
    BrowserModule,                // Requerido para aplicaciones en el navegador
    AppRoutingModule,             // Configuración del enrutamiento
    HttpClientModule,             // Para manejar peticiones HTTP
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]       // Componente raíz
})
export class AppModule { }
