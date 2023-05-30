/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.entities.dto;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.entities.Producto;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Producto_AdicionesDto implements Serializable{
    private Producto producto;
    private List<Adicion> adiciones;
}
