import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { TipoProducto } from 'src/restaurantes/entidades/TipoProducto.entity';
import { CrearTipoProdParams } from 'src/restaurantes/utils/types';
import { Repository } from 'typeorm';

@Injectable()
export class TipoProductoServicioService {
  constructor(@InjectRepository(TipoProducto) private tipoProdRepo: Repository<TipoProducto>){

  }
  findTipoProd(){
    return this.tipoProdRepo.find();
  }
  crearTipoProd(tipoProdDetalles: CrearTipoProdParams){
    const newTipoProd = this.tipoProdRepo.create({...tipoProdDetalles});
    return this.tipoProdRepo.save(newTipoProd);
  }
  eliminarTipoProd( id: number){
    return this.tipoProdRepo.delete(id);
  }
}
