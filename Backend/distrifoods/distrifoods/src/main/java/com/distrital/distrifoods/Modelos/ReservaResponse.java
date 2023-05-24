package com.distrital.cinedistrito.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaResponse {
    private Integer id;
    private Integer numeroSilla;
    private String nombrePelicula;
    private Integer numeroSala;
    private Long cedula;
    private String Nombre;
    private String nombreSede;
}
