import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContratosArrendadorComponent } from './contratos-arrendador/contratos-arrendador.component';
import { LoginRegisterComponent } from './login-register/login-register.component'; // Componente de login
import { ContratosArrendatarioComponent } from './contratos-arrendatario/contratos-arrendatario.component';
import { PrincipalArrendadorComponent } from './principal-arrendador/principal-arrendador.component';
import { PrincipalArrendatarioComponent } from './principal-arrendatario/principal-arrendatario.component';
import { PropiedadesArrendadorComponent } from './propiedades-arrendador/propiedades-arrendador.component';
import { CrearPropiedadComponent } from './propiedades-arrendador/crear-propiedad/crear-propiedad.component'; // Importar el componente

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginRegisterComponent },
  { path: 'arrendador/:idArrendador/contratos', component: ContratosArrendadorComponent },
  { path: 'arrendatario/:idArrendatario/contratos', component: ContratosArrendatarioComponent},
  { path: 'arrendador/:idArrendador', component: PrincipalArrendadorComponent },
  { path: 'arrendatario/:idArrendatario', component: PrincipalArrendatarioComponent },
  { path: 'arrendador/:idArrendador/propiedades', component: PropiedadesArrendadorComponent },
  { path: 'arrendador/:idArrendador/crear-propiedad', component: CrearPropiedadComponent }
  // Otras rutas...
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
