/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.entities.dto;

import com.app.distrifoods.app.entities.Estado;
import com.app.distrifoods.app.entities.MetodoPago;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDto_Consulta implements Serializable{
    private Integer id;
    private ClienteDto cliente;
    private MetodoPago metodoPago;
    private Date fecha;
    private Estado estado;
    private Float precio;
    private List<ProductoCompletoDto> productos;
}
