import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'pedidos'})
export class Pedido{
  @PrimaryGeneratedColumn()
  id_pedido: number;
  @Column()
  id_cliente: number;
  @Column()
  id_met_pago: number;
  @Column()
  fecha: Date;
  @Column()
  id_estado: number;
  @Column()
  precio: number;
}
