import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Producto } from 'src/app/models/producto';
import { environment } from 'src/environments/environment';
import { Adicion, AdicionType2 } from 'src/app/models/adicion';
import { SesionService } from '../../shared/service/sesion.service';

@Injectable({
  providedIn: 'root'
})
export class EdicionAdicionServicioService {
  public endpoint_adicion_listar: string = "adicion/all/";
  public endpoint_adicion_listarMapeado: string = "adicion/all/mapeado/";
  public endpoint_adicion_guardar: string = "adicion/save";
  public endpoint_adicion: string = "adicion/";
  public endpoint_adicion_byId: string = "adicion/byid/";
  public endpoint_adicion_actualizar: string = "adicion/update/";

  constructor(private httpClient: HttpClient, private sesionService: SesionService) { }

  getAdicionesPorProducto(){
    return this.httpClient.get<Adicion[]>(environment.endpoint + this.endpoint_adicion_listarMapeado);
  }

  getAdicionesMapeadas(){
    return this.httpClient.get<AdicionType2[]>(environment.endpoint + this.endpoint_adicion_listarMapeado);
  }

  getAdiciones(){
    return this.httpClient.get<Adicion[]>(environment.endpoint + this.endpoint_adicion_listar);
  }

  getAdicionesById(id: number){
    return this.httpClient.get<Adicion>(environment.endpoint + this.endpoint_adicion_byId + id);
  }

  crearAdicion(adicion: Adicion, options?: any){
    return this.httpClient.post<Producto>(environment.endpoint + this.endpoint_adicion_guardar, adicion, this.crearHeader());
  }

  deleteAdicion(id: number){
    return this.httpClient.delete<boolean>(environment.endpoint + this.endpoint_adicion + id, this.crearHeader());
  }

  actualizarAdicion(adicion: Adicion, options?: any){
    return this.httpClient.put<Adicion>(environment.endpoint + this.endpoint_adicion_actualizar, adicion, this.crearHeader());
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
