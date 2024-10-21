import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContratosArrendadorComponent } from './contratos-arrendador/contratos-arrendador.component';
import { LoginRegisterComponent } from './login-register/login-register.component'; // Componente de login
import { ContratosArrendatarioComponent } from './contratos-arrendatario/contratos-arrendatario.component';
import { PrincipalArrendadorComponent } from './principal-arrendador/principal-arrendador.component';
import { PrincipalArrendatarioComponent } from './principal-arrendatario/principal-arrendatario.component';
import { PropiedadesArrendadorComponent } from './propiedades-arrendador/propiedades-arrendador.component';
import { CrearPropiedadComponent } from './propiedades-arrendador/crear-propiedad/crear-propiedad.component';
import { DetallePropiedadComponent } from './propiedades-arrendador/detalle-propiedad/detalle-propiedad.component';
import { EditarPropiedadComponent } from './propiedades-arrendador/editar-propiedad/editar-propiedad.component';
import { SolicitarArriendoComponent } from './solicitar-arriendo/solicitar-arriendo.component'; // Importa el componente
import { CalificarArrendadorComponent } from './calificar-arrendador/calificar-arrendador.component';
import { CalificarArrendatarioComponent } from './calificar-arrendatario/calificar-arrendatario.component';
import { PaginaPrincipalComponent } from './Landing-Page/pagina-principal/pagina-principal.component';


const routes: Routes = [
  { path: '', component: PaginaPrincipalComponent },
  { path: 'login', component: LoginRegisterComponent },
  { path: 'arrendador/:idArrendador/contratos', component: ContratosArrendadorComponent },
  { path: 'arrendatario/:idArrendatario/contratos', component: ContratosArrendatarioComponent },
  { path: 'arrendador/:idArrendador', component: PrincipalArrendadorComponent },
  { path: 'arrendatario/:idArrendatario', component: PrincipalArrendatarioComponent },
  { path: 'arrendador/:idArrendador/propiedades', component: PropiedadesArrendadorComponent },
  { path: 'arrendador/:idArrendador/propiedades/crear-propiedad', component: CrearPropiedadComponent },
  { path: 'arrendador/:idArrendador/propiedades/detalle-propiedad/:idPropiedad', component: DetallePropiedadComponent},
  { path: 'arrendador/:idArrendador/propiedades/editar-propiedad/:idPropiedad', component: EditarPropiedadComponent},
  { path: 'solicitar-arriendo/:id', loadComponent: () => import('./solicitar-arriendo/solicitar-arriendo.component').then(m => m.SolicitarArriendoComponent) },
  { path: 'arrendatario/:idArrendatario/calificar', component: CalificarArrendadorComponent },
  { path: 'arrendador/:idArrendador/calificar', component: CalificarArrendatarioComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }