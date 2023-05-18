export type CrearRestParams = {
  nom_restaurante: string;
  imagen: string;
}

export type ActRestParams = {
  nom_restaurante: string;
}

export type CrearEstadoParams = {
  nom_estado: string;
}
export type CrearSucursalParams = {
  id_restaurante: number;
  direccion: string;
}
export type CrearTipoProdParams = {
  nom_tipo_prod: string;
}
export type CrearAdicionParams = {
  id_producto: number;
  nom_adicion: string;
  precio: number;
}
export type CrearProductoParams = {
  sucursal: number;
  nom_producto: string;
  tipo: number;
  cantidad: number;
  descripcion: string;
  imagen: string;
  precio: number;
}
export type CrearPedidoParams = {
  id_cliente: number;
  id_met_pago: number;
  fecha: Date;
  id_estado: number;
  precio: number;
}
export type CrearDetProdParams = {
  id_detalle_pedido: number;
  id_adicion: number;
}
export type CrearDetPedidoParams = {
  id_pedido: number;
  id_producto: number;
}
export type CrearMetPagoParams = {
  nom_met_pago: string;
}
