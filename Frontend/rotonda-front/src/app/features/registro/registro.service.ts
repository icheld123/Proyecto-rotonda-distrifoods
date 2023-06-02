import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TipoProducto } from 'src/app/models/tipoProducto';
import { Producto, ProductoCompleto} from 'src/app/models/producto';
import { SesionService } from '../shared/service/sesion.service';
import { Cliente, ClienteCompleto } from 'src/app/models/cliente';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {

  public endpoint_usuario_guardar: string = "cliente/registro/";

  constructor(private httpClient: HttpClient, private sesionService: SesionService) { }

  crearUsuario(cliente: ClienteCompleto, options?: any){
    return this.httpClient.post<Cliente>(environment.endpoint + this.endpoint_usuario_guardar,
      cliente, this.crearHeader());
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
