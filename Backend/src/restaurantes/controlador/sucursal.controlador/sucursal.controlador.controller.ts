import { Controller, Post, Body, Get } from '@nestjs/common';
import { CrearSucursalDto } from 'src/restaurantes/dtos/CrearSucursal.dto';
import { SucursalesServicioService } from 'src/restaurantes/servicios/sucursales.servicio/sucursales.servicio.service';

@Controller('restaurante/sucursal')
export class SucursalControladorController {
  constructor(private sucursalService: SucursalesServicioService){

  }
  @Post()
  crearSucursal(@Body() crearSucursalDto: CrearSucursalDto){
    return this.sucursalService.crearSucursal(crearSucursalDto);
  }
  @Get()
  async getSucursal(){
    return await this.sucursalService.findSucursal();
  }
}
