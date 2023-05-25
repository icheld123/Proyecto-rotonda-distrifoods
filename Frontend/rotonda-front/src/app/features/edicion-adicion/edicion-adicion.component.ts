import { Component } from '@angular/core';
import { Adicion, AdicionType2 } from 'src/app/models/adicion';
import { Producto } from 'src/app/models/producto';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RestauranteService } from '../restaurantes/restaurante.service';
import { EdicionAdicionServicioService } from './service/edicion-adicion.servicio.service';
import { Restaurante } from 'src/app/models/restaurante';
import { MenuServicioService } from '../menu/service/menu.servicio.service';
import { HttpHeaders } from '@angular/common/http';
import { EdicionMenuService } from '../edicion-menu/service/edicion-menu.service';

const CABECERA = ["ID", "PRODUCTO", "RESTAURANTE", "NOMBRE ADICION", "PRECIO", "ACCION"];

@Component({
  selector: 'app-edicion-adicion',
  templateUrl: './edicion-adicion.component.html',
  styleUrls: ['./edicion-adicion.component.css']
})
export class EdicionAdicionComponent {
  public formSelect: FormGroup;
  public formAdicion: FormGroup;
  public formAdicionAct: FormGroup;
  public listarProductos: boolean = true;
  public crearProductos: boolean = false;
  public actualizarProductos: boolean = false;
  public cabecera: string[] = CABECERA;
  productosPorRestaurante: Producto[] = [];
  restaurantes: Restaurante[] = [];
  // sucursales: Sucursal[] = [];
  adiciones: AdicionType2[] = [];
  adicionesSinMapear: Adicion[] = [];
  adicionActual: Adicion;
  adicionesFiltrados: AdicionType2[] = [];
  idRestauranteActual: number;
  nombreRestauranteActual: string;
  prodSelecconado: number = 0;

  constructor(private edicionAdicionService: EdicionAdicionServicioService,
              private restauranteService: RestauranteService,
              private menuServicioService: MenuServicioService,
              private edicionMenuService: EdicionMenuService){}


  public mostrarProductos(){
    this.listarProductos = true;
    this.crearProductos = false;
    this.actualizarProductos = false;
    this.llamarServicioGetAdiciones();
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
    let selectedRestaurante = document.getElementById("restauranteFiltro") as HTMLFormElement
    let selectedProducto = document.getElementById("productoFiltro") as HTMLFormElement
    if (this.adiciones.length > 0 && selectedRestaurante["value"] != "" && selectedProducto["value"] != ""){
      this.adicionesFiltrados = [];
      for (let index = 0; index < this.adicionesSinMapear.length; index++) {
        const adicion = this.adicionesSinMapear[index];
        if (adicion.idProducto == selectedProducto["value"]){
          this.adicionesFiltrados.push(this.adiciones[index]);
        }
      }
    }

  }

  // async llamarServicioGetTiposProductos(){
  //   this.edicionMenuService.getTiposProductos().subscribe(respuesta => {
  //     this.tipoproductos = respuesta;
  //     // console.log(this.tipoproductos);
  //   })
  // }

  async llamarServicioGetAdiciones(){
    this.edicionAdicionService.getAdicionesMapeadas().subscribe(respuesta => {
      this.adiciones = respuesta;
      this.adicionesFiltrados = this.adiciones;
      // console.log(this.adicionesFiltrados);
    })

    this.edicionAdicionService.getAdiciones().subscribe(respuesta => {
      this.adicionesSinMapear = respuesta;
      // console.log(this.adicionesSinMapear);
    })
  }

  async llamarServicioGetAdicionById(id: number){
    this.edicionAdicionService.getAdicionesById(id).subscribe(respuesta => {
      this.adicionActual = respuesta;
      // console.log(this.adicionActual);
      this.llamarServicioGetProductoById(this.adicionActual.idProducto);
    })
  }

  async llamarServicioGetProductoById(id: number){
    this.edicionMenuService.getProductosById(id).subscribe(respuesta => {
      this.idRestauranteActual = respuesta.idRestaurante;
      this.restaurantes.forEach(restaurante => {
        if(restaurante.id == this.idRestauranteActual){
          this.nombreRestauranteActual = restaurante.nombre;
        }
      });
      // console.log(this.idRestauranteActual);
      this.llamarServicioGetAdicion(this.idRestauranteActual.toString(), true);
    })
  }

  async llamarServicioGetRestaurantes(){
    this.restauranteService.getRestaurantes().subscribe(respuesta => {
        this.restaurantes = respuesta;
        // console.log(this.restaurantes);
      });
  }

  async iniciarServicioGetAdicion(value: boolean){
    let selectedRestaurante = (value) ? document.getElementById("restauranteFiltro") as HTMLFormElement : document.getElementById("restauranteSave") as HTMLFormElement;
    this.llamarServicioGetAdicion(selectedRestaurante["value"], false);
  }

  async llamarServicioGetAdicion(id: string, llamarSeteo: boolean){
    this.menuServicioService.getProductos(id).subscribe(respuesta => {
      this.productosPorRestaurante = respuesta;
      if (llamarSeteo){
        this.setearFormProducto();
      }
    });
  }

  public llamarServicioDeleteAdicion(id: number){
    // let eliminar = confirm("¿Está seguro de eliminar el producto (id:"+ id +")?");
    // console.log(eliminar)
    if (confirm("¿Está seguro de eliminar la adición (id:"+ id +")?")) {
      this.edicionAdicionService.deleteAdicion(id).subscribe(respuesta => {
        console.log(respuesta);
        this.llamarServicioGetAdiciones();
      });
    }
  }

  public llamarServicioPostAdicion(){
    console.log(this.formAdicion.value);
    if (this.formAdicion.valid) {
      let options = {
        headers: new HttpHeaders().set(
          'Content-Type',
          'application/json'
        )
      };
      try {
        this.edicionAdicionService.crearAdicion(this.formAdicion.value, options).subscribe({
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

  public llamarServicioUpdateAdicion(){
    if (this.formAdicionAct.valid) {
      console.log(this.formAdicionAct.value);
      let options = {
        headers: new HttpHeaders().set(
          'Content-Type',
          'application/json'
        )
      };
      try {
        this.edicionAdicionService.actualizarAdicion(this.formAdicionAct.value, options).subscribe({
          next: data => {
            console.log(data);
            this.llamarServicioGetAdiciones();
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

  public async IniciarUpdateAdicion(id: number){
    this.mostrarActProductos();
    await this.llamarServicioGetAdicionById(id);
  }

  async llamarServicioGetAdicion2(){
    this.menuServicioService.getProductos(this.formSelect.value.restauranteUpdate).subscribe(respuesta => {
      console.log(respuesta)
      this.productosPorRestaurante = respuesta;
    });

  }

  public setearFormProducto(){

    this.formSelect.controls['restauranteUpdate'].setValue(this.idRestauranteActual);

    this.formAdicionAct.controls['id'].setValue(this.adicionActual.id);
    this.formAdicionAct.controls['idProducto'].setValue(this.adicionActual.idProducto);
    this.formAdicionAct.controls['nombre'].setValue(this.adicionActual.nombre);
    this.formAdicionAct.controls['precio'].setValue(this.adicionActual.precio);
  }

  public construirFormulario(){
    this.formAdicion = new FormGroup({
      idProducto: new FormControl("", [Validators.required]),
      nombre: new FormControl("", [Validators.required]),
      precio: new FormControl("", [Validators.required])
    });

    this.formAdicionAct = new FormGroup({
      id: new FormControl("", Validators.required),
      idProducto: new FormControl("", [Validators.required]),
      nombre: new FormControl("", [Validators.required]),
      precio: new FormControl("", [Validators.required])
    });

    this.formSelect = new FormGroup({
      restauranteUpdate: new FormControl("", Validators.required)
    });


  }



  ngOnInit(): void {
    this.construirFormulario();
    this.llamarServicioGetAdiciones();
    this.llamarServicioGetRestaurantes();
  }
}
