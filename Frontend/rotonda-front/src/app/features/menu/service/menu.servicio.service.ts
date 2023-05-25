import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from 'src/app/models/producto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MenuServicioService {

  public endpoint_productos: string = "producto/all/byrestaurant/";

  constructor(private httpClient: HttpClient) { }

  getProductos(id: string){
    return this.httpClient.get<Producto[]>(environment.endpoint + this.endpoint_productos + id);
  }

}
