import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Adicion } from '../../models/adicion';
import { Producto } from 'src/app/models/producto';

@Injectable({
  providedIn: 'root'
})
export class EdicionAdicionServicioService {
  constructor(private httpClient: HttpClient) { }
  getAdiciones(){
    return this.httpClient.get<Adicion[]>('http://localhost:3000/restaurante/adicion');
  }
  getProductos(){
    return this.httpClient.get<Producto[]>('http://localhost:3000/restaurante/sucursal/producto');
  }
  crearAdicion( adicion: Adicion){
    return this.httpClient.post<Adicion>('http://localhost:3000/restaurante/adicion', adicion);
  }
}
