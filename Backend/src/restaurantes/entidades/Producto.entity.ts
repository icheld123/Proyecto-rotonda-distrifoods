import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({name: 'productos'})
export class Producto{
  @PrimaryGeneratedColumn()
  id_producto: number;
  @Column({nullable: false})
  sucursal: number;
  @Column()
  nom_producto: string;
  @Column()
  tipo: number;
  @Column()
  cantidad: number;
  @Column()
  descripcion: string;
  @Column()
  imagen: string;
  @Column()
  precio: number;
}
