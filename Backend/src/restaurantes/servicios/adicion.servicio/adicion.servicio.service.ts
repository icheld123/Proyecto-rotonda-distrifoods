import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Adiciones } from 'src/restaurantes/entidades/Adicion.entity';
import { CrearAdicionParams } from 'src/restaurantes/utils/types';

@Injectable()
export class AdicionServicioService {

  constructor(@InjectRepository(Adiciones) private adicionRepo: Repository<Adiciones>){

  }
  findAdicion(){
    return this.adicionRepo.find();
  }
  crearAdicion(adicionDetalles: CrearAdicionParams){
    const newAdicion = this.adicionRepo.create({...adicionDetalles});
    return this.adicionRepo.save(newAdicion);
  }
  eliminarAdicion( id: number){
    return this.adicionRepo.delete(id);
  }
}
