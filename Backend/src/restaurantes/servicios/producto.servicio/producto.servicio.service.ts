import { Injectable, } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { NotFoundError } from 'rxjs';
import {Producto } from 'src/restaurantes/entidades/Producto.entity';
import { CrearProductoParams } from 'src/restaurantes/utils/types';
import { Repository } from 'typeorm';

@Injectable()
export class ProductoServicioService {
  constructor(
    @InjectRepository(Producto) private productoRepo: Repository<Producto>,
  ){}

  findOne(id: number): Promise<Producto> {
    const producto = this.productoRepo.findOne({
      where: {id_producto: id}
    });
    if(!producto){
      throw new NotFoundError('Producto no encontrado')
    }
    return producto;
  }

  crearProducto(prodDetalles: CrearProductoParams){
    const newProducto = this.productoRepo.create({...prodDetalles});
    return this.productoRepo.save(newProducto);
  }
  findProducto() {
    return this.productoRepo.find();
  }
}
