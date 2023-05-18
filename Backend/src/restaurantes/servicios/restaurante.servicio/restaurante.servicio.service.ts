import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Restaurante } from 'src/restaurantes/entidades/Restaurante.entity';
import { CrearRestParams, ActRestParams } from 'src/restaurantes/utils/types';

@Injectable()
export class RestauranteServicioService {

  constructor(@InjectRepository(Restaurante) private restRepo: Repository<Restaurante>){

  }
  findOne(id: number){
    const restaurante = this.restRepo.find({where:{id_restaurante: id}});
    if(!restaurante){
      throw new NotFoundException('El restaurante ${id} no existe')
    }
    return restaurante;
  }

  findRestaurante(){
    return this.restRepo.find();
  }
  crearRestaurante(restDetalles: CrearRestParams){
    const newRest = this.restRepo.create({...restDetalles});
      return this.restRepo.save(newRest);
  }
  actualizarRestaurante( id: number, actualizarRestDet: ActRestParams){
    return this.restRepo.update(id, {...actualizarRestDet});
  }
  eliminarRestaurante( id: number){
    return this.restRepo.delete(id);
  }
}
