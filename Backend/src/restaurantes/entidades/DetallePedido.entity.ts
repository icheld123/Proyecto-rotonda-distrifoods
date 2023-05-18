import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'detalles_producto'})
export class DetalleProducto{
  @PrimaryGeneratedColumn()
  id_detalle_prod: number;
  @Column()
  id_detalle_pedido: number;
  @Column()
  id_adicion: number;
}
