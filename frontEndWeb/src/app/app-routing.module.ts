import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContratosArrendadorComponent } from './contratos-arrendador/contratos-arrendador.component';
import { LoginComponent } from './login/login.component'; // Componente de login
import { ContratosArrendatarioComponent } from './contratos-arrendatario/contratos-arrendatario.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'arrendador/:idArrendador/contratos', component: ContratosArrendadorComponent },
  {path: 'arrendatario/:idArrendatario/contratos', component: ContratosArrendatarioComponent}
  // Otras rutas...
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
