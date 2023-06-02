import { Component } from '@angular/core';
import { RestauranteService } from './restaurante.service';
import { Restaurante } from '../../models/restaurante';
import { Sucursal } from '../../models/sucursal';

@Component({
  selector: 'app-restaurantes',
  templateUrl: './restaurantes.component.html',
  styleUrls: ['./restaurantes.component.css']
})
export class RestaurantesComponent {
  restaurantes: Restaurante[] = [];
  sucursales: Sucursal[] = [];
  sucursalesMostrar: Sucursal[] = [];
  public mostrarModal: boolean = false;

  constructor(private restService: RestauranteService){

  }

  async llamarServicioGetRestaurantes(){
    this.restService.getRestaurantes().subscribe(respuesta => {
        this.restaurantes = respuesta;
        console.log(this.restaurantes);
      })
  }

  async llamarServicioGetSucursales(){
    this.restService.getSucursales().subscribe(respuesta => {
      this.sucursales = respuesta;
      console.log(this.sucursales);
    })
  }

  public abrirModal(idRestaurante: number){
    this.definirSucursalesMostrar(idRestaurante);
    this.mostrarModal = true;
  }

  public definirSucursalesMostrar(idRestaurante: number){
    this.sucursalesMostrar = [];
    this.sucursales.forEach(sucursal => {
      if (sucursal.id_restaurante == idRestaurante){
        this.sucursalesMostrar.push(sucursal);
      }
    });
  }

  public cerrarModal(){
    this.mostrarModal = false;
  }

  ngOnInit(): void {
    this.llamarServicioGetRestaurantes();
    this.llamarServicioGetSucursales();
  }
}
