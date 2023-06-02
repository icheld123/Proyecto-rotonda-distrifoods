import { Component, OnInit } from '@angular/core';
import { Producto, ProductoCompleto } from 'src/app/models/producto';
import { CartService } from '../shared/service/cart.service';
import { CarritoService } from './service/carrito.service';
import { Usuario, UsuarioLogueadoResponse } from 'src/app/models/usuario';
import { Cliente } from 'src/app/models/cliente';
import { MetodoPago } from 'src/app/models/metodoPago';
import { Pedido } from 'src/app/models/pedido';
import { HttpHeaders } from '@angular/common/http';
import { Adicion } from 'src/app/models/adicion';
import { credencialesAnonimo } from 'src/environments/environment';
import { Router } from '@angular/router';
import { SesionService } from '../shared/service/sesion.service';


const INCONSISTENCIAS_PEDIDO = "Hay inconsistencias en el pedido, por favor asegúrese de tener productos en el carrito y haber elegido el metodo de pago.";
const NO_ES_CLIENTE = "Por favor inicie sesión o registrese para poder realizar la compra.";
const MENSAJE_PRODUCTOS_INSUFICIENTES = "En su carrito tiene productos que no tienen stock, desea retirarlos?";
const CREACION_CORRECTA = "El pedido se ha creado exitosamente.";
const CREACION_INCORRECTA = "No fue posible crear el pedido, probablemente hay productos sin stock. Recargue la página y retire los productos repetidos";
const ESTADO_INICIAL_EN_PROCESO:number = 1;

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit{
  items:ProductoCompleto[] = [];
  total:number = 0;
  buttonPedir: boolean = false;
  existeCliente: boolean = false;
  usuario: Usuario;
  cliente: Cliente;
  metodosPago: MetodoPago[];
  public existeUnaSesionCliente: boolean;
  public sesionData: UsuarioLogueadoResponse;
  public esCliente: boolean = false;

  constructor(public cartService: CartService,
              public carritoService: CarritoService,
              public sesionService: SesionService){
  }

  removeFromCart(item: ProductoCompleto) {
    this.cartService.removeItem(item);
    this.items = this.cartService.getItems();
    this.calcularTotal();
  }

  clearCart(items: ProductoCompleto[]) {
    // this.items.forEach((item, index) => this.cartService.removeItem(index));
    this.cartService.clearCart(items);
    this.items = [...this.cartService.getItems()];
    this.calcularTotal();
  }

  calcularTotal(){
    this.total = 0;
    this.items.forEach(producto => {
      this.total = this.total + producto.precio;
      if (producto.adiciones != null && producto.adiciones.length > 0){
        producto.adiciones.forEach(adicion => {
          this.total = this.total + adicion.precio;
        });
      }
    });
  }

  calcularProductoConAdiciones(tproducto:number, adiciones:Adicion[]){
    let suma = 0;
    if (adiciones.length > 0){
      adiciones.forEach(adicion => {
        suma = suma + adicion.precio;
      });
    }
    return suma + tproducto;
  }

  validarCliente(){
    this.existeCliente = false;
    this.buttonPedir = false;
    let inputIdentificacion = document.getElementById("identificacion") as HTMLFormElement
    if (inputIdentificacion["value"] != ""){
      this.llamarServicioGetUsuarioByIdentificacion(inputIdentificacion["value"]);
    }
  }

  llamarServicioGetUsuarioByIdentificacion(id: number){
    this.carritoService.getUsuarioByIdentificacion(id).subscribe(respuesta => {
      this.usuario = respuesta;
      this.llamarServicioGetClientByIdUsuario(this.usuario.id);
    });
  }

  llamarServicioGetClientByIdUsuario(id: number){
    this.carritoService.getClientByIdUsuario(id).subscribe(respuesta => {
      this.cliente = respuesta
      this.existeCliente = true;
      this.buttonPedir = true;
    });
  }

  llamarServicioGetMetodoPago(){
    this.carritoService.getMetodoPago().subscribe(respuesta => {
      // console.log(respuesta);
      this.metodosPago = respuesta;
    });
  }

  llamarServicioPostValidarProductos(){
    let options = {
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization': 'Basic ' + btoa(credencialesAnonimo.usuario + ":" + credencialesAnonimo.contrasena)
      })
    };
    // console.log(options);
    try {
      this.carritoService.validarProductos(this.items, options).subscribe(
          (response: any) =>{
            if(response.length > 0){
              if(confirm(MENSAJE_PRODUCTOS_INSUFICIENTES)){
                this.removerListaProductos(response);
              }
            }
          }),
          (error: any) => {
            console.log(error);
          }
    } catch (error) {
      console.log(error);
    }
  }

  removerListaProductos(producto: Producto[]){
    producto.forEach(element => {
      while(this.cartService.itemInCartById(element.id)){
        this.cartService.removeItemById(element.id);
      }
    });
  }

  llamarServicioPostPedido(productos: ProductoCompleto[], metodoPago: number){
    let pedido: Pedido = new Pedido(this.sesionData.cliente.id, metodoPago, new Date(), ESTADO_INICIAL_EN_PROCESO, this.total, productos)
    console.log(pedido);
    let options = {
      headers: new HttpHeaders().set(
        'Content-Type',
        'application/json'
      )
    };
    try {
      this.carritoService.crearPedido(pedido, options).subscribe(
          (response: any) =>{
            if (response.id > 0){
              this.mostrarMensaje(CREACION_CORRECTA);
              this.clearCart(this.items);
            }
            else{
              this.mostrarMensaje(CREACION_INCORRECTA);
            }
          }),
          (error: any) => {
            console.log(error);
          }
    } catch (error) {
      console.log(error);
    }
  }

  iniciarPedido(){
    if (this.validarExistenciaSesionCliente()){
      let metodoPago = document.getElementById("metodo") as HTMLFormElement;
      let productos: ProductoCompleto[] = this.cartService.getItems();

      if (metodoPago["value"] > 0 && this.sesionData.cliente != null && this.sesionData.cliente.id > 0 && this.total > 0 && productos.length > 0){
        this.llamarServicioPostPedido(productos, metodoPago["value"]);
      }
      else {
        this.mostrarMensaje(INCONSISTENCIAS_PEDIDO);
      }
    }
    else{
      this.mostrarMensaje(NO_ES_CLIENTE);
    }

  }

  public validarExistenciaSesionCliente(): boolean{
    this.sesionData = this.sesionService.getSesionData();
    return (this.sesionData != null && this.sesionData.cliente != null && this.sesionData.identificacion > 0) ? true : false;
  }

  public mostrarMensaje(mensaje: string){
    alert(mensaje);
  }

  ngOnInit(): void {
    this.cartService.loadCart();
    this.items = this.cartService.getItems();
    this.calcularTotal();
    this.llamarServicioGetMetodoPago();
    this.llamarServicioPostValidarProductos();
    this.existeUnaSesionCliente = this.validarExistenciaSesionCliente();
  }

}
