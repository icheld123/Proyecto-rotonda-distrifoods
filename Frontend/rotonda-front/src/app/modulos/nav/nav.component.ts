import { Component } from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {
  logo = 'assets/img/logo.png';
  menu = [
    {nombre: 'Restaurantes',
    url:'/restaurantes'},
    {nombre: 'Carrito',
    url:'/carrito'},
    {nombre: 'Registrarme',
    url:'registro'},
    {nombre: 'Iniciar sesión',
    url:'iniciosesion'}
  ];
  submenu = [
    {nombre: 'Administrar menú',
    url:'/administrar-menu'},
    {nombre: 'Administrar adiciones',
    url:'/administrar-adicion'}
  ];
}
