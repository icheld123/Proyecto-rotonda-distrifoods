import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Adicion } from 'src/app/models/adicion';
import { ProductoCompleto } from 'src/app/models/producto';
import { Restaurante } from 'src/app/models/restaurante';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MenuServicioService {

  public endpoint_productos: string = "producto/all/byrestaurant/";
  public endpoint_producto: string = "producto/byId/";
  public endpoint_restaurante: string = "restaurante/byId/";
  public endpoint_adiciones_byproducto: string = "/all/byproducto/";

  constructor(private httpClient: HttpClient) { }

  getProductos(id: string){
    return this.httpClient.get<ProductoCompleto[]>(environment.endpoint + this.endpoint_productos + id);
  }

  getProductoById(id: string){
    return this.httpClient.get<ProductoCompleto>(environment.endpoint + this.endpoint_producto + id);
  }

  getRestauranteById(id: string){
    return this.httpClient.get<Restaurante>(environment.endpoint + this.endpoint_restaurante + id);
  }

}
