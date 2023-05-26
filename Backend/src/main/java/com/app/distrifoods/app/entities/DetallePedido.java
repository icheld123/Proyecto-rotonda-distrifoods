/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="detalles_pedido")
public class DetallePedido implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
    @Column(name="id_detalle_pedido")
    private Integer id;
    @Column(name="id_pedido", nullable=false)
    private Integer idPedido;
    @Column(name="id_producto", nullable=false)
    private Integer idProducto;
}
