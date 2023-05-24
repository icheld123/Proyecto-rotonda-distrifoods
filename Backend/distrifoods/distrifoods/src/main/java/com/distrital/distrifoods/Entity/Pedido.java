package com.distrital.cinedistrito.Entity;

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
public class Pedido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //genera automáticamente el ID
    @Column()
    private Integer id_pedido;
    @Column(nullable=false)
    private Integer id_cliente;
    @Column(nullable=false)
    private Integer id_met_pago;
    @Column(nullable=false)
    private Date fecha;
    @Column(nullable=false)
    private Integer id_estado;
    @Column(nullable=false)
    private Integer precio_total;

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id_pedido +
                ", id_cliente=" + id_cliente +
                ", id_met_pago=" + id_met_pago +
                ", fecha=" + fecha +
                ", id_estado=" + id_estado +
                ", precio_total=" + precio_total +
                '}';
    }
}


