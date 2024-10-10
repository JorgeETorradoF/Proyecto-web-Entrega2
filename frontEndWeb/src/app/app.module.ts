import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Importar FormsModule

import { AppComponent } from './app.component'; // Componente principal
import { ContratosArrendadorComponent } from './contratos-arrendador/contratos-arrendador.component'; // Componente de contratos
import { ContratosArrendatarioComponent } from './contratos-arrendatario/contratos-arrendatario.component';
import { PropiedadesArrendadorComponent } from './propiedades-arrendador/propiedades-arrendador.component';
import { CrearPropiedadComponent } from './propiedades-arrendador/crear-propiedad/crear-propiedad.component';

@NgModule({
  declarations: [
    AppComponent,
    ContratosArrendadorComponent,
    ContratosArrendatarioComponent,
    PropiedadesArrendadorComponent,
    CrearPropiedadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    RouterModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

