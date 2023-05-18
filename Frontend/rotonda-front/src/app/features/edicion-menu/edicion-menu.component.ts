import { Component } from '@angular/core';
import {Producto} from '../../models/producto';
import {EdicionMenuService} from './edicion-menu.service';
import { Sucursal } from 'src/app/models/sucursal';
import { Restaurante } from 'src/app/models/restaurante';
import { RestauranteService } from '../restaurantes/restaurante.service';

@Component({
  selector: 'app-edicion-menu',
  templateUrl: './edicion-menu.component.html',
  styleUrls: ['./edicion-menu.component.css']
})
export class EdicionMenuComponent {
  productos: Producto[] = [];
  restaurantes: Restaurante[] = [];
  sucursales: Sucursal[] = [];

  prodNuevo: Producto = {
    id_producto: 0,
    sucursal: 0,
    nom_producto: '',
    tipo: 0,
    cantidad: 0,
    descripcion: '',
    imagen: '',
    precio: 0,

  }
  prodSelecconado: number = 0;

  constructor(private edicionMenuService: EdicionMenuService, private restauranteService: RestauranteService){

  }
  seleccionado(value: string): void {
    this.prodSelecconado = Number(value);
    console.log(this.prodSelecconado);
  }

  async llamarServicioGetProducto(){
    this.edicionMenuService.getProductos().subscribe(respuesta => {
      this.productos = respuesta;
    })
  }

  async getSucursales(){
    this.restauranteService.getSucursales().subscribe(respuesta => {
      this.sucursales = respuesta;
    })
  }
  async getRestaurantes(){
    this.restauranteService.getRestaurantes().subscribe(respuesta => {
      this.restaurantes = respuesta;
    })
  }


  private mapearProductos(productos: Producto[]){
    return productos;
  }
  private mapearRestaurantes(restaurantes: Restaurante[]){
    return restaurantes;
  }
  private mapearSucursales(sucursales: Sucursal[]){
    return sucursales;
  }

  submitProducto(){
    this.edicionMenuService.crearProducto(this.prodNuevo)
    .subscribe( res=>{
      console.log(res);
      //this.router.navigate(['/']);
    },
    err=>console.log(err)
    )
  }

  ngOnInit(): void {
    this.llamarServicioGetProducto();
    this.getSucursales();
    this.getRestaurantes();
  }
}
