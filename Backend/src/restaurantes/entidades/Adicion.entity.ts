import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'adiciones'})
export class Adiciones{
  @PrimaryGeneratedColumn()
  id_adicion: number;
  @Column()
  id_producto: number;
  @Column()
  nom_adicion: string;
  @Column()
  precio: number;
}
