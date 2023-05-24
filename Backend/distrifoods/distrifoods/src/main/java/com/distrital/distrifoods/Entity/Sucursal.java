package com.distrital.cinedistrito.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="sucursales")
public class Sucursal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //genera automáticamente el ID
    @Column()
    private Integer id_sucursal;
    @Column(nullable=false)
    private String direccion;
    @Column(nullable=false)
    private Integer id_restaurante;
}
