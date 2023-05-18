import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Restaurante } from '../../models/restaurante';
import { Sucursal } from '../../models/sucursal';

@Injectable({
  providedIn: 'root'
})
export class RestauranteService {

  constructor(private httpClient: HttpClient) { }
  getRestaurantes(){
    return this.httpClient.get<Restaurante[]>('http://localhost:3000/restaurante');
  }
  getSucursales(){
    return this.httpClient.get<Sucursal[]>('http://localhost:3000/restaurante/sucursal');
  }
}
