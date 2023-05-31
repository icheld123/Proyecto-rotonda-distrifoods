import { Adicion } from "./adicion";
import { Restaurante } from "./restaurante";
import { TipoProducto } from "./tipoProducto";

export interface Producto {
  id: number;
  idRestaurante: number;
  nombre: string;
  idTipoProducto: number;
  cantidad: number;
  descripcion: string;
  imagen: string;
  precio: number;
}

export interface ProductoCompleto {
  id: number;
  restaurante: Restaurante;
  nombre: string;
  tipoProducto: TipoProducto;
  cantidad: number;
  descripcion: string;
  imagen: string;
  precio: number;
  adiciones: Adicion[];
}
