import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterEvent } from '@angular/router';
import { SesionService } from 'src/app/features/shared/service/sesion.service';
import { UsuarioLogueadoResponse } from 'src/app/models/usuario';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit{

  public existeUnaSesion: boolean;
  public sesionData: UsuarioLogueadoResponse;
  public esAdmin: boolean;
  public mostrarRegistrar: boolean;

  cantidadItems: number = 0;
  logo = 'assets/img/logo.png';
  menu = [
    {nombre: 'Restaurantes',
    url:'/restaurantes'},
    {nombre: 'Carrito',
    url:'/carrito'},
    {nombre: 'Registrarme',
    url:'/registro'},
    {nombre: 'Iniciar sesión',
    url:'/login'}
  ];
  submenu = [
    {nombre: 'Administrar menú',
    url:'/administrar-menu'},
    {nombre: 'Administrar adiciones',
    url:'/administrar-adicion'},
    {nombre: 'Administrar pedidos',
    url:'/pedidos'}
  ];

  constructor(private router: Router, public sesionService: SesionService){
    router.events.subscribe(s => {
      this.recontarItems();
      this.validarExistenciaSesion();
    });
  }

  public validarExistenciaSesion(){
    this.sesionData = this.sesionService.getSesionData();
    this.existeUnaSesion = (this.sesionData != null && this.sesionData.identificacion > 0) ? true : false;
    // console.log("Existe sesion: " + this.existeUnaSesion);

    if (this.existeUnaSesion && this.sesionData.cliente == null){
      this.esAdmin = true;
      this.mostrarRegistrar = true;
    }
    else if (this.existeUnaSesion && this.sesionData.cliente != null){
      this.esAdmin = false;
      this.mostrarRegistrar = false;
    }
    else{
      this.esAdmin = false;
      this.mostrarRegistrar = true;
    }
  }


  recontarItems(){
    let contenidoLocalStorage = localStorage.getItem("cart_items");
    if (contenidoLocalStorage){
      this.cantidadItems = (JSON.parse(contenidoLocalStorage) ?? []).length;
    }
  }


  ngOnInit(): void {
    this.recontarItems();
    this.validarExistenciaSesion();
  }

}
