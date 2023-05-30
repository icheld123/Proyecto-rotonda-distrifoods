import { ClienteCompleto } from "./cliente";
import { Estado } from "./estado";
import { MetodoPago } from "./metodoPago";
import { ProductoAdicionType2, ProductoCompleto } from "./producto";

export class Pedido {
  idCliente:  number;
  idMetodo: number;
  fecha: Date;
  idEstado: number;
  precio: number;
  idsPedidos: ProductoAdicionType2[];

  constructor(idCliente:  number, idMetodo: number, fecha: Date, idEstado: number, precio: number, idsPedidos: ProductoAdicionType2[]){
    this.idCliente = idCliente;
    this.idMetodo = idMetodo;
    this.fecha = fecha;
    this.idEstado = idEstado;
    this.precio = precio;
    this.idsPedidos = idsPedidos;
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
