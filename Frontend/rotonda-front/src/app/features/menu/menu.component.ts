import { Component } from '@angular/core';
import { Producto } from '../../models/producto';
import { MenuServicioService } from './menu.servicio.service';
import { Sucursal } from 'src/app/models/sucursal';
import { Restaurante } from 'src/app/models/restaurante';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  productos: Producto[] = [];
  sucursales: Sucursal[] = [];
  restaurantes: Restaurante[] = [];
  prodSelecconado: number = 0;

  constructor(private menuService: MenuServicioService){

  }
  seleccionado(value: string): void {
    this.prodSelecconado = Number(value);
    console.log(this.prodSelecconado);
  }

  async llamarServicioGetMenu(){
    this.menuService.getProductos().subscribe(respuesta => {
      this.productos = respuesta;
    })
  }

  private mapearProductos(productos: Producto[]){
    return productos;
  }
  async llamarServicioGetSucursal(){
    this.menuService.getProductos().subscribe(respuesta => {
      this.productos = respuesta;
    })
  }

  private mapearSucursal(productos: Producto[]){
    return productos;
  }
  async llamarServicioGetRestaurante(){
    this.menuService.getProductos().subscribe(respuesta => {
      this.productos = respuesta;
    })
  }

  private mapearRestaurante(productos: Producto[]){
    return productos;
  }
  ngOnInit(): void {
    this.llamarServicioGetMenu();
    this.llamarServicioGetSucursal();
    this.llamarServicioGetRestaurante();
  }
}
