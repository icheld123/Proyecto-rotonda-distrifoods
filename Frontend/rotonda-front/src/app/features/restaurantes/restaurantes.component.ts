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

  constructor(private restService: RestauranteService){

  }

  async llamarServicioGetRestaurantes(){
    this.restService.getRestaurantes().subscribe(
      respuesta => {this.restaurantes = respuesta;})
  }

  async llamarServicioGetSucursales(){
    this.restService.getSucursales().subscribe(respuesta => {
      this.sucursales = respuesta;
    })
  }

  private mapearRestaurantes(restaurantes: Restaurante[]){
    return restaurantes;
  }
  private mapearSucursales(sucursales: Sucursal[]){
    return sucursales;
  }

  ngOnInit(): void {
    this.llamarServicioGetRestaurantes();
    this.llamarServicioGetSucursales();
  }
}
