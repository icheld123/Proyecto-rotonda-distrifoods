import { Controller, Get, Post, Patch, Delete, Body, Param, ParseIntPipe } from '@nestjs/common';
import { ActualizarRestauranteDto } from 'src/restaurantes/dtos/ActualizarRestaurante.dto';
import { CrearRestauranteDto } from 'src/restaurantes/dtos/CrearRestaurante.dto';
import { RestauranteServicioService } from 'src/restaurantes/servicios/restaurante.servicio/restaurante.servicio.service';

@Controller('restaurante')
export class RestauranteControladorController {
  constructor(private restService: RestauranteServicioService){

  }
  @Get()
  async getRestaurantes(){
    return await this.restService.findRestaurante();
  }
  // @Get(':id')
  // async getRestauranteById(@Param('id') id: number){
  //   return await this.restService.findOne(id);
  // }
  @Post()
  crearRestaurante(@Body() crearRestauranteDto: CrearRestauranteDto){
    return this.restService.crearRestaurante(crearRestauranteDto);
  }
  @Patch(':id')
  async actualizarRestauranteId(@Param('id', ParseIntPipe)id: number, @Body() actualizarUser: ActualizarRestauranteDto){
    await this.restService.actualizarRestaurante(id, actualizarUser);
  }
  @Delete(':id')
  async eliminarRestauranteId(@Param('id', ParseIntPipe)id: number){
    await this.restService.eliminarRestaurante(id);
  }
}
