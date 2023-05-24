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
