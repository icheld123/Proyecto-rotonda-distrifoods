import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from '../../models/producto';
import { Sucursal } from 'src/app/models/sucursal';
import { Restaurante } from 'src/app/models/restaurante';

@Injectable({
  providedIn: 'root'
})
export class EdicionMenuService {

  //BASE_URL: string = 'http://localhost:3000/restaurante';

  constructor(private httpClient: HttpClient) { }
  getProductos(){
    return this.httpClient.get<Producto[]>('http://localhost:3000/restaurante/sucursal/producto');
  }
  getProducto(id_producto: number){
    return this.httpClient.get<Producto>('http://localhost:3000/restaurante/sucursal/producto');
  }
  getSucursales(){
    return this.httpClient.get<Sucursal>('http://localhost:3000/restaurante/sucursal');
  }
  getRestaurantes(){
    return this.httpClient.get<Restaurante>('http://localhost:3000/restaurante');
  }
  crearProducto( producto: Producto){
    return this.httpClient.post<Producto>('http://localhost:3000/restaurante/sucursal/producto', producto);
  }
  deleteProducto(id: number){
    return this.httpClient.delete<Producto>('http://localhost:3000/restaurante/sucursal/producto/?id_producto=${id}');
  }
  actualizarProducto(id: number, producto: Producto){
    return this.httpClient.put<Producto>('http://localhost:3000/restaurante/sucursal/producto/?id_producto=${id}', producto);
  }

}
