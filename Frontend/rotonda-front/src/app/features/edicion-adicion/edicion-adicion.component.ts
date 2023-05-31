import { Component } from '@angular/core';
import { Adicion, AdicionType2 } from 'src/app/models/adicion';
import { Producto, ProductoCompleto } from 'src/app/models/producto';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RestauranteService } from '../restaurantes/restaurante.service';
import { EdicionAdicionServicioService } from './service/edicion-adicion.servicio.service';
import { Restaurante } from 'src/app/models/restaurante';
import { MenuServicioService } from '../menu/service/menu.servicio.service';
import { HttpHeaders } from '@angular/common/http';
import { EdicionMenuService } from '../edicion-menu/service/edicion-menu.service';

const ACTUALIZACION_CORRECTA = "El producto fue actualizado satisfactoriamente.";
const CREACION_CORRECTA = "El producto ha sido actualizado correctamente.";
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
  productosPorRestaurante: ProductoCompleto[] = [];
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


  public mostrarAdiciones(){
    this.listarProductos = true;
    this.crearProductos = false;
    this.actualizarProductos = false;
    this.llamarServicioGetAdiciones();
  }

  public mostrarCrearAdiciones(){
    this.listarProductos = false;
    this.crearProductos = true;
    this.actualizarProductos = false;
  }

  public mostrarActAdiciones(){
    this.listarProductos = false;
    this.crearProductos = false;
    this.actualizarProductos = true;
  }


  async filtrar(){
    // let selectedRestaurante = document.getElementById("restauranteFiltro") as HTMLFormElement
    // let selectedProducto = document.getElementById("productoFiltro") as HTMLFormElement
    // if (this.adiciones.length > 0 && selectedRestaurante["value"] != "" && selectedProducto["value"] != ""){
    //   this.adicionesFiltrados = [];
    //   for (let index = 0; index < this.adicionesSinMapear.length; index++) {
    //     const adicion = this.adicionesSinMapear[index];
    //     if (adicion.idProducto == selectedProducto["value"]){
    //       this.adicionesFiltrados.push(this.adiciones[index]);
    //     }
    //   }
    // }
    let selectedRestaurante = document.getElementById("restauranteFiltro") as HTMLFormElement
    let selectedProducto = document.getElementById("productoFiltro") as HTMLFormElement
    if (selectedRestaurante["value"] != "" && selectedProducto["value"] != ""){
      this.adicionesFiltrados = [];
      this.productosPorRestaurante.forEach(producto => {
          producto.adiciones.forEach(adicion => {
            if(adicion.idProducto == selectedProducto["value"]){
              this.adicionesFiltrados.push({id: adicion.id,
                                            nombreProducto: producto.nombre,
                                            nombreRestaurante: producto.restaurante.nombre,
                                            nombre: adicion.nombre,
                                            precio: adicion.precio});
            }
          });
      });
    }
    console.log(this.adicionesFiltrados);

  }

  public mostrarMensaje(mensaje: string){
    alert(mensaje);
  }

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
      });
  }

  async iniciarServicioGetAdicion(value: boolean){
    let selectedRestaurante = (value) ? document.getElementById("restauranteFiltro") as HTMLFormElement : document.getElementById("restauranteSave") as HTMLFormElement;
    this.llamarServicioGetAdicion(selectedRestaurante["value"], false);
  }

  async llamarServicioGetAdicion(id: string, llamarSeteo: boolean){
    this.menuServicioService.getProductos(id).subscribe(respuesta => {
      this.productosPorRestaurante = respuesta;
      console.log(this.productosPorRestaurante)
      if (llamarSeteo){
        this.setearFormProducto();
      }
    });
  }

  public llamarServicioDeleteAdicion(id: number){
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
        this.edicionAdicionService.crearAdicion(this.formAdicion.value, options).subscribe(
          (response: any) =>{
            if (response.id > 0){
              this.mostrarMensaje(CREACION_CORRECTA);
              this.mostrarAdiciones();

            }
          }),
          (error: any) => {
            console.log(error);
          }
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
        this.edicionAdicionService.actualizarAdicion(this.formAdicionAct.value, options).subscribe(
          (response: any) =>{
            if (response.id > 0){ //NECESARIO REVISAR Y AJUSTAR VALIDACIÓN
              this.mostrarMensaje(ACTUALIZACION_CORRECTA);
              this.mostrarAdiciones();
            }
          }),
          (error: any) => {
            console.log(error);
          }
      } catch (error) {
        console.log(error);
      }
    }
  }

  public async IniciarUpdateAdicion(id: number){
    this.mostrarActAdiciones();
    await this.llamarServicioGetAdicionById(id);
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
