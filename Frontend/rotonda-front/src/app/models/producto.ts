import { Adicion } from "./adicion";

export interface Producto {
  id: number;
  nombreRestaurante: string;
  nombreProducto: string;
  tipoProducto: string;
  cantidad: number;
  descripcion: string;
  imagen: string;
  precio: number;
}

export interface ProductoType2 {
  id: number;
  idRestaurante: number;
  nombre: string;
  idTipoProducto: number;
  cantidad: number;
  descripcion: string;
  imagen: string;
  precio: number;
}

export interface ProductoAdicion {
  id: number;
  nombreRestaurante: string;
  nombreProducto: string;
  tipoProducto: string;
  cantidad: number;
  adiciones: Adicion[];
  descripcion: string;
  imagen: string;
  precio: number;
}

export interface ProductoAdicionType2 {
  id: number;
  adiciones: Adicion[];
}

export interface ProductoCompleto {
  producto: ProductoType2;
  adiciones: Adicion[];
}
