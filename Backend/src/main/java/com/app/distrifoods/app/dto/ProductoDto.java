/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.dto;

import com.app.distrifoods.app.entities.Restaurante;
import com.app.distrifoods.app.entities.TipoProducto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDto {
    private Integer id;
    private Restaurante restaurante;
    private String nombre;
    private TipoProducto tipoProducto;
    private Integer cantidad;
    private String descripcion;
    private String imagen;
    private Float precio;
}
