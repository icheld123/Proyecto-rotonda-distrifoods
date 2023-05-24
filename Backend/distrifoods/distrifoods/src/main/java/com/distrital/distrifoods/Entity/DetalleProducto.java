package com.distrital.cinedistrito.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="detalles_producto")
public class DetalleProducto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //genera automáticamente el ID
    @Column()
    private Integer id_det_producto;
    @Column(nullable=false)
    private Integer id_det_pedido;
    @Column()
    private Integer id_adicicon;
}
