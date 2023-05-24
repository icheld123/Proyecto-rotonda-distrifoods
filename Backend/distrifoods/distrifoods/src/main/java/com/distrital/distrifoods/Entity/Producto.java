package com.distrital.cinedistrito.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="productos")
public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //genera automáticamente el ID
    @Column()
    private Integer id_producto;
    @Column(nullable=false)
    private String nom_producto;
    @Column(nullable=false)
    private Integer tipo_prod;
    @Column(nullable=false)
    private Integer cantidad;
    @Column(nullable=false)
    private float precio;
}
