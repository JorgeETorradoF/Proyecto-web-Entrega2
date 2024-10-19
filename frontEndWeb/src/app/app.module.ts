import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ContratosArrendadorComponent } from './contratos-arrendador/contratos-arrendador.component';
import { ContratosArrendatarioComponent } from './contratos-arrendatario/contratos-arrendatario.component';
import { PropiedadesArrendadorComponent } from './propiedades-arrendador/propiedades-arrendador.component';
import { CrearPropiedadComponent } from './propiedades-arrendador/crear-propiedad/crear-propiedad.component';
import { LoginRegisterComponent } from './login-register/login-register.component';
import { DetallePropiedadComponent } from './propiedades-arrendador/detalle-propiedad/detalle-propiedad.component';
import { EditarPropiedadComponent } from './propiedades-arrendador/editar-propiedad/editar-propiedad.component';
import { PrincipalArrendatarioComponent } from './principal-arrendatario/principal-arrendatario.component';
@NgModule({
  declarations: [
    AppComponent,
    ContratosArrendadorComponent,
    ContratosArrendatarioComponent,
    CrearPropiedadComponent,
    PropiedadesArrendadorComponent,
    LoginRegisterComponent,
    DetallePropiedadComponent,
    EditarPropiedadComponent,
    PrincipalArrendatarioComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
