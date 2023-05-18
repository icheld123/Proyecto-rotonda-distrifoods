import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'tipos_producto'})
export class TipoProducto{
  @PrimaryGeneratedColumn()
  id_tipo_prod: number;
  @Column()
  nom_tipo_prod: string;
}
