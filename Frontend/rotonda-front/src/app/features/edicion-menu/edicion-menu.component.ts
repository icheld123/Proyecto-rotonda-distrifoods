import { Component } from '@angular/core';
import {EdicionMenuService} from './edicion-menu.service';
import { Sucursal } from 'src/app/models/sucursal';
import { Restaurante } from 'src/app/models/restaurante';
import { RestauranteService } from '../restaurantes/restaurante.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TipoProducto } from 'src/app/models/tipoProducto';
import { HttpHeaders } from '@angular/common/http';
import { Producto, ProductoType2 } from 'src/app/models/producto';

const CABECERA = ["ID", "RESTAURANTE", "PRODUCTO", "TIPO PRODUCTO", "CANTIDAD", "DESCRIPCIÓN", "IMÁGEN", "PRECIO", "ACCION"];

@Component({
  selector: 'app-edicion-menu',
  templateUrl: './edicion-menu.component.html',
  styleUrls: ['./edicion-menu.component.css']
})
export class EdicionMenuComponent {
  public formProducto: FormGroup;
  public formProductoAct: FormGroup;
  public listarProductos: boolean = true;
  public crearProductos: boolean = false;
  public actualizarProductos: boolean = false;
  public cabecera: string[] = CABECERA;
  tipoproductos: TipoProducto[] = [];
  restaurantes: Restaurante[] = [];
  sucursales: Sucursal[] = [];
  productos: Producto[] = [];
  productoActual: ProductoType2;
  productosFiltrados: Producto[] = [];
  prodSelecconado: number = 0;

  constructor(private edicionMenuService: EdicionMenuService, private restauranteService: RestauranteService){}


  public mostrarProductos(){
    this.listarProductos = true;
    this.crearProductos = false;
    this.actualizarProductos = false;
    this.llamarServicioGetProductos();
  }

  public mostrarCrearProductos(){
    this.listarProductos = false;
    this.crearProductos = true;
    this.actualizarProductos = false;
  }

  public mostrarActProductos(){
    this.listarProductos = false;
    this.crearProductos = false;
    this.actualizarProductos = true;
  }


  async filtrar(){
    //await this.llamarServicioGetProductos();
    let valueSelect = document.getElementById("restauranteFiltro") as HTMLFormElement
    if (this.productos.length > 0 && valueSelect["value"] != ""){
      this.productosFiltrados = [];
      this.productos.forEach(producto => {
        if (producto.nombreRestaurante.toLowerCase() == valueSelect["value"].toLowerCase()){
          this.productosFiltrados.push(producto);
        }
      });
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
      // console.log(this.productosFiltrados);
    })
  }

  async llamarServicioGetProductoById(id: number){
    this.edicionMenuService.getProductosById(id).subscribe(respuesta => {
      this.productoActual = respuesta;
      // console.log(this.productoActual);
      this.setearFormProducto();
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

  public llamarServicioUpdateProducto(){
    if (this.formProductoAct.valid) {
      console.log(this.formProductoAct.value);
      let options = {
        headers: new HttpHeaders().set(
          'Content-Type',
          'application/json'
        )
      };
      try {
        this.edicionMenuService.actualizarProducto(this.formProductoAct.value, options).subscribe({
          next: data => {
            console.log(data);
            this.llamarServicioGetProductos();
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

  public async IniciarUpdateProducto(id: number){
    this.mostrarActProductos();
    await this.llamarServicioGetProductoById(id);

  }

  public setearFormProducto(){
    this.formProductoAct.controls['id'].setValue(this.productoActual.id);
    this.formProductoAct.controls['idRestaurante'].setValue(this.productoActual.idRestaurante);
    this.formProductoAct.controls['nombre'].setValue(this.productoActual.nombre);
    this.formProductoAct.controls['idTipoProducto'].setValue(this.productoActual.idTipoProducto);
    this.formProductoAct.controls['cantidad'].setValue(this.productoActual.cantidad);
    this.formProductoAct.controls['descripcion'].setValue(this.productoActual.descripcion);
    this.formProductoAct.controls['imagen'].setValue(this.productoActual.imagen);
    this.formProductoAct.controls['precio'].setValue(this.productoActual.precio);
  }

  public construirFormulario(){
    this.formProducto = new FormGroup({
      idRestaurante: new FormControl("", [Validators.required]),
      nombre: new FormControl("", [Validators.required]),
      idTipoProducto: new FormControl("", [Validators.required]),
      cantidad: new FormControl("", [Validators.required]),
      descripcion: new FormControl("", [Validators.required]),
      imagen: new FormControl("", [Validators.required]),
      precio: new FormControl("", [Validators.required])
    });

    this.formProductoAct = new FormGroup({
      id: new FormControl("", Validators.required),
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
