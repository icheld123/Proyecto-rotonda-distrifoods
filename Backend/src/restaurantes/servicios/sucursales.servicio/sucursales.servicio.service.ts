import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Sucursal } from 'src/restaurantes/entidades/Sucursal.entity';
import { Repository } from 'typeorm';
import { CrearSucursalParams } from 'src/restaurantes/utils/types';

@Injectable()
export class SucursalesServicioService {
  constructor(
    @InjectRepository(Sucursal) private sucursalRepo: Repository<Sucursal>,
  ){}

  findOne(id: number) {
   return this.sucursalRepo.findOne({
      where: {id_sucursal: id}
    });
  }

  crearSucursal(sucDetalles: CrearSucursalParams){
    const newSucursal = this.sucursalRepo.create({...sucDetalles});
    return this.sucursalRepo.save(newSucursal);
  }
  findSucursal() {
    return this.sucursalRepo.find();
  }
}
