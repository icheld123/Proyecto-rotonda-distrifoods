import { Component } from '@angular/core';
import {EdicionMenuService} from './edicion-menu.service';
import { Sucursal } from 'src/app/models/sucursal';
import { Restaurante } from 'src/app/models/restaurante';
import { RestauranteService } from '../restaurantes/restaurante.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TipoProducto } from 'src/app/models/tipoProducto';
import { HttpHeaders } from '@angular/common/http';
import { Producto } from 'src/app/models/producto';

const CABECERA = ["ID", "RESTAURANTE", "PRODUCTO", "TIPO PRODUCTO", "CANTIDAD", "DESCRIPCIÓN", "IMÁGEN", "PRECIO", "ACCION"];

@Component({
  selector: 'app-edicion-menu',
  templateUrl: './edicion-menu.component.html',
  styleUrls: ['./edicion-menu.component.css']
})
export class EdicionMenuComponent {
  public formProducto: FormGroup;
  public listarProductos: boolean = true;
  public cabecera: string[] = CABECERA;
  tipoproductos: TipoProducto[] = [];
  restaurantes: Restaurante[] = [];
  sucursales: Sucursal[] = [];
  productos: Producto[] = [];
  productosFiltrados: Producto[] = [];
  prodSelecconado: number = 0;

  constructor(private edicionMenuService: EdicionMenuService, private restauranteService: RestauranteService){}


  public mostrarProductos(){
    this.listarProductos = true;
    this.llamarServicioGetProductos();
  }

  public ocultarProductos(){
    this.listarProductos = false;
  }


  async filtrar(){
    //await this.llamarServicioGetProductos();
    let valueSelect = document.getElementById("restauranteFiltro") as HTMLFormElement
    if (this.productos.length > 0 && valueSelect["value"] != ""){
      this.productosFiltrados = [];
      this.productos.forEach(producto => {
        console.log(producto.nombreRestaurante.toLowerCase());
        console.log(valueSelect["value"].toLowerCase());
        if (producto.nombreRestaurante.toLowerCase() == valueSelect["value"].toLowerCase()){
          this.productosFiltrados.push(producto);
        }
      });
      console.log(this.productosFiltrados);
    }

  }

  async llamarServicioGetTiposProductos(){
    this.edicionMenuService.getTiposProductos().subscribe(respuesta => {
      this.tipoproductos = respuesta;
      // console.log(this.tipoproductos);
    })
  }

  async llamarServicioGetProductos(){
    this.edicionMenuService.getProductos().subscribe(respuesta => {
      this.productos = respuesta;
      this.productosFiltrados = this.productos;
      console.log(this.productosFiltrados);
    })
  }

  async llamarServicioGetRestaurantes(){
    this.restauranteService.getRestaurantes().subscribe(respuesta => {
        this.restaurantes = respuesta;
        // console.log(this.restaurantes);
      });
  }

  public llamarServicioDeleteProducto(id: number){
    // let eliminar = confirm("¿Está seguro de eliminar el producto (id:"+ id +")?");
    // console.log(eliminar)
    if (confirm("¿Está seguro de eliminar el producto (id:"+ id +")?")) {
      this.edicionMenuService.deleteProducto(id).subscribe(respuesta => {
        console.log(respuesta);
        this.llamarServicioGetProductos();
      });
    }
  }

  public llamarServicioPostProducto(){
    console.log(this.formProducto.value);
    if (this.formProducto.valid) {
      let options = {
        headers: new HttpHeaders().set(
          'Content-Type',
          'application/json'
        )
      };
      try {
        this.edicionMenuService.crearProducto(this.formProducto.value, options).subscribe({
          next: data => {
            console.log(data);
          },
          error: error =>{
            console.log(error);
          }
        })
      } catch (error) {
        console.log(error);
      }
    }
  }

  public construirFormulario(){

    this.formProducto = new FormGroup({
      // id: new FormControl("", Validators.required),
      idRestaurante: new FormControl("", [Validators.required]),
      nombre: new FormControl("", [Validators.required]),
      idTipoProducto: new FormControl("", [Validators.required]),
      cantidad: new FormControl("", [Validators.required]),
      descripcion: new FormControl("", [Validators.required]),
      imagen: new FormControl("", [Validators.required]),
      precio: new FormControl("", [Validators.required])
    });
  }

  ngOnInit(): void {
    this.construirFormulario();
    this.llamarServicioGetProductos();
    this.llamarServicioGetRestaurantes();
    this.llamarServicioGetTiposProductos();
  }
}
