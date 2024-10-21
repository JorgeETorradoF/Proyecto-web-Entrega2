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
import { CalificarArrendadorComponent } from './calificar-arrendador/calificar-arrendador.component';
import { CalificarArrendatarioComponent } from './calificar-arrendatario/calificar-arrendatario.component';
import { PaginaPrincipalComponent } from './Landing-Page/pagina-principal/pagina-principal.component';
import { NavbarComponent } from './Landing-Page/navbar/navbar.component';
import { HomeComponent } from './Landing-Page/home/home.component';
import { AboutUsComponent } from './Landing-Page/about-us/about-us.component';
import { FooterComponent } from './Landing-Page/footer/footer.component'

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
    CalificarArrendadorComponent,
    CalificarArrendatarioComponent,
    PaginaPrincipalComponent,  // Declaración de PaginaPrincipal
    NavbarComponent,           // Declaración de Navbar
    HomeComponent,             // Declaración de Home
    AboutUsComponent,          // Declaración de About Us
    FooterComponent,           // Declaración de Footer
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
