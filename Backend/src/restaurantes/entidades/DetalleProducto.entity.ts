import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'detalles_pedido'})
export class DetallePedido{
  @PrimaryGeneratedColumn()
  id_detalle_pedido: number;
  @Column()
  id_pedido: number;
  @Column()
  id_producto: number;
}
