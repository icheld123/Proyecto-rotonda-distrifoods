
import { Controller, Get, Post, Delete, Body, ParseIntPipe, Param } from '@nestjs/common';
import { AdicionServicioService } from 'src/restaurantes/servicios/adicion.servicio/adicion.servicio.service';
import { CrearAdicion } from 'src/restaurantes/dtos/CrearAdicion.dto';


@Controller('restaurante/adicion')
export class AdicionController {
  constructor(private adicionService: AdicionServicioService){

  }
  @Get()
  async getAdicion(){
    return await this.adicionService.findAdicion();
  }
  @Post()
  crearAdicion(@Body() crearAdicionDto: CrearAdicion){
    return this.adicionService.crearAdicion(crearAdicionDto);
  }
  // @Patch(':id')
  // async actualizarAdicionId(@Param('id', ParseIntPipe)id: number, @Body() actualizarAdicion: CrearAdicion){
  //   await this.adicionService.actualizarAdicionId(id, actualizarAdicion);
  // }
  @Delete(':id')
  async eliminarAdicionId(@Param('id', ParseIntPipe)id: number){
    await this.adicionService.eliminarAdicion(id);
  }
}
