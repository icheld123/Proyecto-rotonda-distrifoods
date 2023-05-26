export class Pedido {
  idCliente:  number;
  idMetodo: number;
  fecha: Date;
  idEstado: number;
  precio: number;
  idsPedidos: number[];

  constructor(idCliente:  number, idMetodo: number, fecha: Date, idEstado: number, precio: number, idsPedidos: number[]){
    this.idCliente = idCliente;
    this.idMetodo = idMetodo;
    this.fecha = fecha;
    this.idEstado = idEstado;
    this.precio = precio;
    this.idsPedidos = idsPedidos;
  }
}
