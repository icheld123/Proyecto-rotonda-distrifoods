import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './modulos/nav/nav.component';
import { InicioComponent } from './features/inicio/inicio.component';
import { RestaurantesComponent } from './features/restaurantes/restaurantes.component';
import { CarritoComponent } from './features/carrito/carrito.component';
import { AdicionesComponent } from './features/adiciones/adiciones.component';
import { HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './modulos/footer/footer.component';
import { EdicionAdicionComponent } from './features/edicion-adicion/edicion-adicion.component';
import { EdicionMenuComponent } from './features/edicion-menu/edicion-menu.component';
import { MenuComponent } from './features/menu/menu.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import { CurrencyPipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    InicioComponent,
    RestaurantesComponent,
    CarritoComponent,
    AdicionesComponent,
    FooterComponent,
    EdicionAdicionComponent,
    EdicionMenuComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [CurrencyPipe],
  bootstrap: [AppComponent],
})
export class AppModule { }
