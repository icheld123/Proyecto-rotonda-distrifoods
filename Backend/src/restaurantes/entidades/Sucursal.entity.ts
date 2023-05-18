import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'sucursales'})
export class Sucursal{
  @PrimaryGeneratedColumn()
  id_sucursal: number;
  @Column()
  direccion: string;
  @Column()
  id_restaurante: number;
}
