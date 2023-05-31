import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Pedido, PedidoActualizar, PedidoCompleto } from 'src/app/models/pedido';
import { Estado } from 'src/app/models/estado';

@Injectable({
  providedIn: 'root'
})
export class PedidosService {

  public endpoint_pedido_listar: string = "pedido/all/";
  public endpoint_estado_listar: string = "estado/all";
  // public endpoint_producto_guardar: string = "producto/save";
  // public endpoint_producto: string = "producto/";
  // public endpoint_producto_por_restaurante: string = "producto/allByRestaurant/";
  public endpoint_pedido_actualizar: string = "pedido/update/";

  constructor(private httpClient: HttpClient) { }

  getPedidos(){
    return this.httpClient.get<PedidoCompleto[]>(environment.endpoint + this.endpoint_pedido_listar);
  }

  getEstados(){
    return this.httpClient.get<Estado[]>(environment.endpoint + this.endpoint_estado_listar);
  }

  actualizarPedido(producto: PedidoActualizar, options?: any){
    return this.httpClient.put<Pedido>(environment.endpoint + this.endpoint_pedido_actualizar, producto, options);
  }

  // getProductosById(id: number){
  //   return this.httpClient.get<ProductoType2>(environment.endpoint + this.endpoint_producto + id);
  // }

  // getProductosByRestaurant(id: number){
  //   return this.httpClient.get<ProductoType2>(environment.endpoint + this.endpoint_producto_por_restaurante + id);
  // }

  // crearProducto(producto: Producto, options?: any){
  //   return this.httpClient.post<Producto>(environment.endpoint + this.endpoint_producto_guardar,
  //                                         producto, options);
  // }

  // deleteProducto(id: number){
  //   return this.httpClient.delete<boolean>(environment.endpoint + this.endpoint_producto + id);
  // }



}
