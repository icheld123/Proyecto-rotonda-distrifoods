import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Producto } from 'src/app/models/producto';
import { environment } from 'src/environments/environment';
import { Adicion, AdicionType2 } from 'src/app/models/adicion';

@Injectable({
  providedIn: 'root'
})
export class EdicionAdicionServicioService {
  public endpoint_adicion_listar: string = "adicion/all/";
  public endpoint_adicion_listarMapeado: string = "adicion/allmapeado/";
  public endpoint_adicion_guardar: string = "adicion/save";
  public endpoint_adicion: string = "adicion/";
  public endpoint_adicion_actualizar: string = "adicion/update/";

  constructor(private httpClient: HttpClient) { }

  getAdicionesMapeadas(){
    return this.httpClient.get<AdicionType2[]>(environment.endpoint + this.endpoint_adicion_listarMapeado);
  }

  getAdiciones(){
    return this.httpClient.get<Adicion[]>(environment.endpoint + this.endpoint_adicion_listar);
  }

  getAdicionesById(id: number){
    return this.httpClient.get<Adicion>(environment.endpoint + this.endpoint_adicion + id);
  }

  crearAdicion(adicion: Adicion, options?: any){
    return this.httpClient.post<Producto>(environment.endpoint + this.endpoint_adicion_guardar, adicion, options);
  }

  deleteAdicion(id: number){
    return this.httpClient.delete<boolean>(environment.endpoint + this.endpoint_adicion + id);
  }

  actualizarAdicion(adicion: Adicion, options?: any){
    return this.httpClient.put<Adicion>(environment.endpoint + this.endpoint_adicion_actualizar, adicion, options);
  }
}
