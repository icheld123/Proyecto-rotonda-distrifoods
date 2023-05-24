import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from '../../models/producto';
import { Sucursal } from 'src/app/models/sucursal';
import { Restaurante } from 'src/app/models/restaurante';
import { environment } from 'src/environments/environment';
import { TipoProducto } from 'src/app/models/tipoProducto';

@Injectable({
  providedIn: 'root'
})
export class EdicionMenuService {

  public endpoint_tipoproducto_listar: string = "tipoproducto/all";
  public endpoint_producto_listar: string = "producto/all";
  public endpoint_producto_guardar: string = "producto/save";
  public endpoint_producto_eliminar: string = "producto/";

  constructor(private httpClient: HttpClient) { }

  getTiposProductos(){
    return this.httpClient.get<TipoProducto[]>(environment.endpoint + this.endpoint_tipoproducto_listar);
  }

  getProductos(){
    return this.httpClient.get<Producto[]>(environment.endpoint + this.endpoint_producto_listar);
  }

  crearProducto(producto: Producto, options?: any){
    return this.httpClient.post<Producto>(environment.endpoint + this.endpoint_producto_guardar,
                                          producto, options);
  }

  deleteProducto(id: number){
    return this.httpClient.delete<boolean>(environment.endpoint + this.endpoint_producto_eliminar + id);
  }

  // actualizarProducto(id: number, producto: Producto){
  //   return this.httpClient.put<Producto>('http://localhost:3000/restaurante/sucursal/producto/?id_producto=${id}', producto);
  // }

}
