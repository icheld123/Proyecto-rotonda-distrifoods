import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'metodos_pago'})
export class MetodoPago{
  @PrimaryGeneratedColumn()
  id_met_pago: number;
  @Column()
  nom_met_pago: string;
}
