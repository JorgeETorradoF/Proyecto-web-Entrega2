import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContratosArrendadorComponent } from './contratos-arrendador/contratos-arrendador.component';
import { LoginComponent } from './login/login.component';
import { ContratosArrendatarioComponent } from './contratos-arrendatario/contratos-arrendatario.component';
import { PropiedadesArrendadorComponent } from './propiedades-arrendador/propiedades-arrendador.component';
import { CrearPropiedadComponent } from './propiedades-arrendador/crear-propiedad/crear-propiedad.component';
import { DetallePropiedadComponent } from './propiedades-arrendador/detalle-propiedad/detalle-propiedad.component';
import { EditarPropiedadComponent } from './propiedades-arrendador/editar-propiedad/editar-propiedad.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'arrendador/:idArrendador/contratos', component: ContratosArrendadorComponent },
  { path: 'arrendatario/:idArrendatario/contratos', component: ContratosArrendatarioComponent },
  { path: 'arrendador/:idArrendador/propiedades', component: PropiedadesArrendadorComponent },
  { path: 'arrendador/:idArrendador/propiedades/crear-propiedad', component: CrearPropiedadComponent },
  { path: 'arrendador/:idArrendador/propiedades/detalle-propiedad/:idPropiedad', component: DetallePropiedadComponent},
  { path: 'arrendador/:idArrendador/propiedades/editar-propiedad/:idPropiedad', component: EditarPropiedadComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
