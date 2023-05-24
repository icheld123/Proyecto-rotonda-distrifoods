/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.entities;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="pedidos")
public class Pedido implements Serializable  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
    @Column(name="id_pedido")
    private Integer id;
    @Column(name="id_cliente", nullable=false)
    private Integer id_cliente;
    @Column(name="id_met_pago", nullable=false)
    private Integer id_metodo;
    @Column(nullable=false)
    private Date fecha;
    @Column(name="id_estado", nullable=false)
    private Integer id_estado;
    @Column(nullable=false)
    private Float precio;
}
