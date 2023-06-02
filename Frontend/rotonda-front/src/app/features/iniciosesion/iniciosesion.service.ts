import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsuarioLogueadoRequest, UsuarioLogueadoResponse } from 'src/app/models/usuario';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InicioSesionService {

  public endpoint_login: string = "login";

  constructor(private httpClient: HttpClient) { }

  login(usuarioLogueadoRequest: UsuarioLogueadoRequest, options?: any){
    return this.httpClient.post<UsuarioLogueadoResponse>(environment.endpoint + this.endpoint_login,
      usuarioLogueadoRequest, options);
  }


}
