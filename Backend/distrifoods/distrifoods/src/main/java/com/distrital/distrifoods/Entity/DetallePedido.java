package com.distrital.cinedistrito.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="detalles_pedido")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //genera automáticamente el ID
    @Column()
    private Integer id_det_pedido;
    @Column(nullable=false)
    private Integer id_pedido;
    @Column(nullable=false)
    private Integer id_producto;
}
