import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Restaurante } from '../../models/restaurante';
import { Sucursal } from '../../models/sucursal';
import { Producto } from 'src/app/models/producto';

@Injectable({
  providedIn: 'root'
})
export class MenuServicioService {

  constructor(private httpClient: HttpClient) { }
  getProductos(){
    return this.httpClient.get<Producto[]>('http://localhost:3000/restaurante/sucursal/producto');
  }
  getSucursales(){
    return this.httpClient.get<Sucursal[]>('http://localhost:3000/restaurante/sucursal/producto');
  }
  getRestaurantes(){
    return this.httpClient.get<Restaurante[]>('http://localhost:3000/restaurante');
  }
}
