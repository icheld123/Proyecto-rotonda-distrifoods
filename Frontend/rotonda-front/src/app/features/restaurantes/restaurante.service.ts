import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Restaurante } from '../../models/restaurante';
import { Sucursal } from '../../models/sucursal';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RestauranteService {

  public endpoint_restaurant: string = "restaurante/all";
  public endpoint_sucursal: string = "sucursal/all";

  constructor(private httpClient: HttpClient) { }

  getRestaurantes(){
    return this.httpClient.get<Restaurante[]>(environment.endpoint + this.endpoint_restaurant);
  }

  getSucursales(){
    return this.httpClient.get<Sucursal[]>(environment.endpoint + this.endpoint_sucursal);
  }
}
