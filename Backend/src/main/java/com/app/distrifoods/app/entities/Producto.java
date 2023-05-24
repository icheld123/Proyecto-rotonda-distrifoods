/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="productos")
public class Producto implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Integer id;
    @Column(name="id_restaurante", nullable=false)
    private Integer idRestaurante;
    @Column(name="nom_producto", nullable=false)
    private String nombre;
    @Column(name="id_tipo", nullable=false)
    private Integer idTipoProducto;
    @Column(nullable=false)
    private Integer cantidad;
    @Column(nullable=false)
    private String descripcion;
    @Column(nullable=false)
    private String imagen;
    @Column(nullable=false)
    private Float precio;
}
