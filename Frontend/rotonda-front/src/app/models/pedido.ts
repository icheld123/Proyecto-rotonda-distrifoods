import { ClienteCompleto } from "./cliente";
import { Estado } from "./estado";
import { MetodoPago } from "./metodoPago";
import { ProductoCompleto } from "./producto";

export class Pedido {
  idCliente:  number;
  idMetodo: number;
  fecha: Date;
  idEstado: number;
  precio: number;
  productos: ProductoCompleto[];

  constructor(idCliente:  number, idMetodo: number, fecha: Date, idEstado: number, precio: number, productos: ProductoCompleto[]){
    this.idCliente = idCliente;
    this.idMetodo = idMetodo;
    this.fecha = fecha;
    this.idEstado = idEstado;
    this.precio = precio;
    this.productos = productos;
  }
}


export interface PedidoCompleto {
  id: number;
  cliente: ClienteCompleto;
  metodoPago: MetodoPago;
  fecha: Date;
  estado: Estado;
  precio: number;
  productos: ProductoCompleto[];
}
