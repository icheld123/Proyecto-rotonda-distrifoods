import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TipoProducto } from 'src/app/models/tipoProducto';
import { Producto, ProductoCompleto} from 'src/app/models/producto';
import { SesionService } from '../../shared/service/sesion.service';

@Injectable({
  providedIn: 'root'
})
export class EdicionMenuService {

  public endpoint_tipoproducto_listar: string = "tipoproducto/all/";
  public endpoint_producto_listar: string = "producto/all/";
  public endpoint_producto_guardar: string = "producto/save";
  public endpoint_producto_byId: string = "producto/byId/";
  public endpoint_producto: string = "producto/";
  public endpoint_producto_por_restaurante: string = "producto/allByRestaurant/";
  public endpoint_producto_actualizar: string = "producto/update/";

  constructor(private httpClient: HttpClient, private sesionService: SesionService) { }

  getTiposProductos(){
    return this.httpClient.get<TipoProducto[]>(environment.endpoint + this.endpoint_tipoproducto_listar);
  }

  getProductos(){
    return this.httpClient.get<ProductoCompleto[]>(environment.endpoint + this.endpoint_producto_listar);
  }

  getProductosById(id: number){
    return this.httpClient.get<Producto>(environment.endpoint + this.endpoint_producto_byId + id);
  }

  getProductosByRestaurant(id: number){
    return this.httpClient.get<Producto>(environment.endpoint + this.endpoint_producto_por_restaurante + id);
  }

  crearProducto(producto: Producto, options?: any){
    return this.httpClient.post<Producto>(environment.endpoint + this.endpoint_producto_guardar,
                                          producto, this.crearHeader());
  }

  deleteProducto(id: number){
    return this.httpClient.delete<boolean>(environment.endpoint + this.endpoint_producto + id, this.crearHeader());
  }

  actualizarProducto(producto: Producto, options?: any){
    return this.httpClient.put<Producto>(environment.endpoint + this.endpoint_producto_actualizar, producto, this.crearHeader());
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
