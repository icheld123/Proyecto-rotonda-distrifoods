import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './features/inicio/inicio.component';
import { RestaurantesComponent } from './features/restaurantes/restaurantes.component';
import { CarritoComponent } from './features/carrito/carrito.component';
import { AdicionesComponent } from './features/adiciones/adiciones.component';
import { EdicionAdicionComponent } from './features/edicion-adicion/edicion-adicion.component';
import { EdicionMenuComponent } from './features/edicion-menu/edicion-menu.component';
import { MenuComponent } from './features/menu/menu.component';
import { PedidosComponent } from './features/pedidos/pedidos.component';

const routes: Routes = [
  { path: '', component:InicioComponent},
  { path: 'restaurantes', component:RestaurantesComponent},
  { path: 'carrito', component:CarritoComponent},
  { path: 'adiciones', component:AdicionesComponent},
  { path: 'administrar-adicion', component: EdicionAdicionComponent },
  { path: 'administrar-menu', component: EdicionMenuComponent },
  { path: 'pedidos', component: PedidosComponent },
  { path: 'restaurantes/menu', component: MenuComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
