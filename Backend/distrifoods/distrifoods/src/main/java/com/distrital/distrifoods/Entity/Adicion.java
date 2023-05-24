package com.distrital.cinedistrito.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="adiciones")
public class Adicion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //genera automáticamente el ID
    @Column()
    private Integer id_adicion;
    @Column(nullable=false)
    private Integer id_producto;
    @Column(nullable=false)
    private String nom_adicion;
    @Column(nullable=false)
    private float precio;
}
