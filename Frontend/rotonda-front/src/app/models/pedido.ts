import { ProductoAdicionType2 } from "./producto";

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
