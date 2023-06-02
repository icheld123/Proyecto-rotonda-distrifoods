import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Pedido, PedidoActualizar, PedidoCompleto } from 'src/app/models/pedido';
import { Estado } from 'src/app/models/estado';
import { SesionService } from '../../shared/service/sesion.service';

@Injectable({
  providedIn: 'root'
})
export class PedidosService {

  public endpoint_pedido_listar: string = "pedido/all/";
  public endpoint_estado_listar: string = "estado/all/";
  public endpoint_pedido_actualizar: string = "pedido/update/";

  constructor(private httpClient: HttpClient, private sesionService: SesionService) { }

  getPedidos(){
    return this.httpClient.get<PedidoCompleto[]>(environment.endpoint + this.endpoint_pedido_listar, this.crearHeader());
  }

  getEstados(){
    return this.httpClient.get<Estado[]>(environment.endpoint + this.endpoint_estado_listar, this.crearHeader());
  }

  actualizarPedido(producto: PedidoActualizar, options?: any){
    if (options == null){
      options = this.crearHeader();
    }
    return this.httpClient.put<Pedido>(environment.endpoint + this.endpoint_pedido_actualizar, producto, options);
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
