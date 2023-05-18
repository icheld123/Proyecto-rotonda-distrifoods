import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'estados'})
export class Estado{
  @PrimaryGeneratedColumn()
  id_estado: number;
  @Column()
  nom_estado: string;
}
