/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="detalles_producto")
public class DetalleProducto implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
    @Column(name="id_detalle_prod")
    private Integer id;
    @Column(name="id_detalle_pedido", nullable=false)
    private Integer idPedido;
    @Column(name="id_adicion", nullable=false)
    private Integer idAdicion;
}
