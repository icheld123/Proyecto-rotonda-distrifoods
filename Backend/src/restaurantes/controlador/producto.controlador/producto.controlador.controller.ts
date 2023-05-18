import { Controller, Post, Get, Body } from '@nestjs/common';
import { CrearProductoDto } from 'src/restaurantes/dtos/CrearProducto.dto';
import { ProductoServicioService } from 'src/restaurantes/servicios/producto.servicio/producto.servicio.service';

@Controller('restaurante/sucursal/producto')
export class ProductoControladorController {
  constructor(private productoService: ProductoServicioService){

  }
  @Post()
  crearProducto(@Body() crearProductoDto: CrearProductoDto){
    return this.productoService.crearProducto(crearProductoDto);
  }
  @Get()
  async getProducto(){
    return await this.productoService.findProducto();
  }
}
