import { Component, OnInit } from '@angular/core';
import { Producto, ProductoCompleto } from 'src/app/models/producto';
import { CartService } from '../shared/service/cart.service';
import { CarritoService } from './service/carrito.service';
import { Usuario } from 'src/app/models/usuario';
import { Cliente } from 'src/app/models/cliente';
import { MetodoPago } from 'src/app/models/metodoPago';
import { Pedido } from 'src/app/models/pedido';
import { HttpHeaders } from '@angular/common/http';
import { Adicion } from 'src/app/models/adicion';


const CREACION_CORRECTA = "El pedido se ha creado exitosamente.";
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

  constructor(public cartService: CartService, public carritoService: CarritoService){
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

  llamarServicioPostMetodoPago(productos: ProductoCompleto[], metodoPago: number){
    let pedido: Pedido = new Pedido(this.cliente.id, metodoPago, new Date(), ESTADO_INICIAL_EN_PROCESO, this.total, productos)
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
          }),
          (error: any) => {
            console.log(error);
          }
    } catch (error) {
      console.log(error);
    }
    console.log(pedido);
  }

  iniciarPedido(){
    let metodoPago = document.getElementById("metodo") as HTMLFormElement;
    let productos: ProductoCompleto[] = this.cartService.getItems();

    if (metodoPago["value"] > 0 && this.cliente != null && this.cliente.id > 0 && this.total > 0 && productos.length > 0){
      this.llamarServicioPostMetodoPago(productos, metodoPago["value"]);
    }
  }

  public mostrarMensaje(mensaje: string){
    alert(mensaje);
  }

  ngOnInit(): void {
    this.cartService.loadCart();
    this.items = this.cartService.getItems();
    console.log(this.items);
    this.calcularTotal();
    this.llamarServicioGetMetodoPago();
  }

}
