import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterEvent } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit{
  cantidadItems: number = 0;
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
    url:'/administrar-adicion'},
    {nombre: 'Administrar pedidos',
    url:'/pedidos'}
  ];

  constructor(private router: Router){
    router.events.subscribe(s => {
      this.recontarItems();
    });
  }

  recontarItems(){
    let contenidoLocalStorage = localStorage.getItem("cart_items");
    if (contenidoLocalStorage){
      this.cantidadItems = (JSON.parse(contenidoLocalStorage) ?? []).length;
    }
  }


  ngOnInit(): void {
    this.recontarItems();
  }

}
