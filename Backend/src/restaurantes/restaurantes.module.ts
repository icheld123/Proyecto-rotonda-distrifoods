import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Restaurante } from './entidades/Restaurante.entity';
import {Sucursal} from './entidades/Sucursal.entity';
import { RestauranteControladorController } from './controlador/restaurante.controlador/restaurante.controlador.controller';
import { RestauranteServicioService } from './servicios/restaurante.servicio/restaurante.servicio.service';
import { SucursalesServicioService } from './servicios/sucursales.servicio/sucursales.servicio.service';
import { SucursalControladorController } from './controlador/sucursal.controlador/sucursal.controlador.controller';
import { Adiciones } from 'src/restaurantes/entidades/Adicion.entity';
import { AdicionController } from './controlador/adicion.controlador/adicion.controlador.controller';
import { AdicionServicioService } from './servicios/adicion.servicio/adicion.servicio.service';
import { MetodosPagoControladorController } from './controlador/metodo_pago.controlador/metodos_pago.controlador.controller';
import { PedidoControladorController } from './controlador/pedido.controlador/pedido.controlador.controller';
import { DetallePedidoControladorController } from './controlador/detalle_pedido.controlador/detalle_pedido.controlador.controller';
import { DetalleProductoControladorController } from './controlador/detalle_producto.controlador/detalle_producto.controlador.controller';
import { EstadoControladorController } from './controlador/estado.controlador/estado.controlador.controller';
import { TipoProductoControladorController } from './controlador/tipo_producto.controlador/tipo_producto.controlador.controller';
import { MetodosPagoServicioService } from './servicios/metodos_pago.servicio/metodos_pago.servicio.service';
import { DetallePedidoServicioService } from './servicios/detalle_pedido.servicio/detalle_pedido.servicio.service';
import { DetalleProductoServicioService } from './servicios/detalle_producto.servicio/detalle_producto.servicio.service';
import { EstadoServicioService } from './servicios/estado.servicio/estado.servicio.service';
import { PedidoServicioService } from './servicios/pedido.servicio/pedido.servicio.service';
import { TipoProductoServicioService } from './servicios/tipo_producto.servicio/tipo_producto.servicio.service';
import { DetalleProducto } from './entidades/DetallePedido.entity';
import { DetallePedido } from './entidades/DetalleProducto.entity';
import { Estado } from './entidades/Estado.entity';
import { MetodoPago } from './entidades/MetodoPago.entity';
import { Producto } from './entidades/Producto.entity';
import { Pedido } from './entidades/Pedido.entity';
import { TipoProducto } from './entidades/TipoProducto.entity';
import { ProductoServicioService } from './servicios/producto.servicio/producto.servicio.service';
import { ProductoControladorController } from './controlador/producto.controlador/producto.controlador.controller';

@Module({
  imports: [TypeOrmModule.forFeature([Restaurante, Sucursal, Adiciones, Producto, DetallePedido, DetalleProducto,
    MetodoPago, Estado, Pedido, TipoProducto])],
  controllers: [RestauranteControladorController, SucursalControladorController, AdicionController, MetodosPagoControladorController, PedidoControladorController, DetallePedidoControladorController, DetalleProductoControladorController, EstadoControladorController, TipoProductoControladorController, ProductoControladorController],
  providers: [RestauranteServicioService, SucursalesServicioService, AdicionServicioService, MetodosPagoServicioService, DetallePedidoServicioService, DetalleProductoServicioService, EstadoServicioService, PedidoServicioService, TipoProductoServicioService, ProductoServicioService],
})
export class RestaurantesModule {}
