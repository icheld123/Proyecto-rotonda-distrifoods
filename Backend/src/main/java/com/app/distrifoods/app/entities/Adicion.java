/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="adiciones")
public class Adicion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_adicion")
    private Integer id;
    @Column(name="id_producto", nullable=false)
    private Integer idProducto;
    @Column(name="nom_adicion", nullable=false)
    private String nombre;
    @Column(nullable=false)
    private Float precio;
}
