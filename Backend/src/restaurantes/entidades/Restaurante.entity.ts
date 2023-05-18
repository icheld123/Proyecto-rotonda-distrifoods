import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'restaurantes'})
export class Restaurante{
  @PrimaryGeneratedColumn()
  id_restaurante: number;
  @Column()
  nom_restaurante: string;
  @Column()
  imagen: string;
}
