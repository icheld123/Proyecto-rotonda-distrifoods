import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Sucursal } from 'src/app/models/sucursal';
import { Restaurante } from 'src/app/models/restaurante';
import { environment } from 'src/environments/environment';
import { TipoProducto } from 'src/app/models/tipoProducto';
import { Producto, ProductoCompleto} from 'src/app/models/producto';
import { Usuario } from 'src/app/models/usuario';
import { Cliente } from 'src/app/models/cliente';
import { MetodoPago } from 'src/app/models/metodoPago';
import { Pedido } from 'src/app/models/pedido';
import { SesionService } from '../../shared/service/sesion.service';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  // public endpoint_tipoproducto_listar: string = "tipoproducto/all/";
  // public endpoint_producto_listar: string = "producto/all/";
  public endpoint_pedido_guardar: string = "pedido/save";
  public endpoint_productos_validar: string = "pedido/validar";
  public endpoint_usuario_por_identificacion: string = "usuario/all/byidentificacion/";
  public endpoint_producto_por_idusuario: string = "cliente/all/byidusuario/";
  public endpoint_metodo_pago: string = "metodopago/all/";

  constructor(private httpClient: HttpClient, private sesionService: SesionService) { }

  getUsuarioByIdentificacion(id: number){
    return this.httpClient.get<Usuario>(environment.endpoint + this.endpoint_usuario_por_identificacion + id, this.crearHeader());
  }

  getClientByIdUsuario(id: number){
    return this.httpClient.get<Cliente>(environment.endpoint + this.endpoint_producto_por_idusuario + id, this.crearHeader());
  }

  getMetodoPago(){
    return this.httpClient.get<MetodoPago[]>(environment.endpoint + this.endpoint_metodo_pago);
  }

  crearPedido(pedido: Pedido, options?: any){
    return this.httpClient.post<void>(environment.endpoint + this.endpoint_pedido_guardar, pedido, this.crearHeader());
  }

  validarProductos(productos: ProductoCompleto[], options?: any){
    return this.httpClient.post<Producto[]>(environment.endpoint + this.endpoint_productos_validar, productos, options);
  }

  crearHeader(){
    let sesionData = this.sesionService.getSesionData();
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(sesionData.usuario + ":" + sesionData.contrasena)
      }
      )
    };

    return options;
  }

}
