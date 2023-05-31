import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Sucursal } from 'src/app/models/sucursal';
import { Restaurante } from 'src/app/models/restaurante';
import { environment } from 'src/environments/environment';
import { TipoProducto } from 'src/app/models/tipoProducto';
import { Producto} from 'src/app/models/producto';
import { Usuario } from 'src/app/models/usuario';
import { Cliente } from 'src/app/models/cliente';
import { MetodoPago } from 'src/app/models/metodoPago';
import { Pedido } from 'src/app/models/pedido';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  // public endpoint_tipoproducto_listar: string = "tipoproducto/all/";
  // public endpoint_producto_listar: string = "producto/all/";
  public endpoint_pedido_guardar: string = "pedido/save";
  // public endpoint_producto: string = "producto/";
  public endpoint_usuario_por_identificacion: string = "usuario/all/byidentificacion/";
  public endpoint_producto_por_idusuario: string = "cliente/all/byidusuario/";
  public endpoint_metodo_pago: string = "metodopago/all/";

  constructor(private httpClient: HttpClient) { }

  getUsuarioByIdentificacion(id: number){
    return this.httpClient.get<Usuario>(environment.endpoint + this.endpoint_usuario_por_identificacion + id);
  }

  getClientByIdUsuario(id: number){
    return this.httpClient.get<Cliente>(environment.endpoint + this.endpoint_producto_por_idusuario + id);
  }

  getMetodoPago(){
    return this.httpClient.get<MetodoPago[]>(environment.endpoint + this.endpoint_metodo_pago);
  }

  crearPedido(pedido: Pedido, options?: any){
    return this.httpClient.post<void>(environment.endpoint + this.endpoint_pedido_guardar, pedido, options);
  }



  // getProductos(){
  //   return this.httpClient.get<Producto[]>(environment.endpoint + this.endpoint_producto_listar);
  // }

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

  // actualizarProducto(producto: Producto, options?: any){
  //   return this.httpClient.put<Producto>(environment.endpoint + this.endpoint_producto_actualizar, producto, options);
  // }

}
