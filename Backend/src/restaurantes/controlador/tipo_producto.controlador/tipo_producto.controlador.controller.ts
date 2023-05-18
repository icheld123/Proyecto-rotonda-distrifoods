import { Controller, Get, Post, Delete, Body,Param, ParseIntPipe } from '@nestjs/common';
import { CrearTiposProdDto } from 'src/restaurantes/dtos/CrearTiposProd.dto';
import { TipoProductoServicioService } from 'src/restaurantes/servicios/tipo_producto.servicio/tipo_producto.servicio.service';

@Controller('restaurante/tipoproducto')
export class TipoProductoControladorController {
  constructor(private tipoProdServicico: TipoProductoServicioService){

  }
  @Get()
  async getTiposProd(){
    return await this.tipoProdServicico.findTipoProd();
  }
  // @Get(':id')
  // async getRestauranteById(@Param('id') id: number){
  //   return await this.restService.findOne(id);
  // }
  @Post()
  crearTipoProd(@Body() crearTipoProdDto: CrearTiposProdDto){
    return this.tipoProdServicico.crearTipoProd(crearTipoProdDto);
  }
  // @Patch(':id')
  // async actualizarTipoProdId(@Param('id', ParseIntPipe)id: number, @Body() crearTipoProdDto: CrearTiposProdDto){
  //   await this.tipoProdServicico.actualizarTipoProd(id, crearTipoProdDto);
  // }
  @Delete(':id')
  async eliminarTipoProdId(@Param('id', ParseIntPipe)id: number){
    await this.tipoProdServicico.eliminarTipoProd(id);
  }
}
