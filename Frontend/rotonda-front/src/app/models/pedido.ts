import { ClienteCompleto } from "./cliente";
import { Estado } from "./estado";
import { MetodoPago } from "./metodoPago";
import { ProductoCompleto } from "./producto";


// PARA GUARDAR PEDIDO
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

// PARA ACTUALIZAR PEDIDO
export interface PedidoActualizar{
  id: number,
  idEstado: number;
}


// PARA OBTENER LISTA DE PEDIDOS
export interface PedidoCompleto {
  id: number;
  cliente: ClienteCompleto;
  metodoPago: MetodoPago;
  fecha: Date;
  estado: Estado;
  precio: number;
  productos: ProductoCompleto[];
}
