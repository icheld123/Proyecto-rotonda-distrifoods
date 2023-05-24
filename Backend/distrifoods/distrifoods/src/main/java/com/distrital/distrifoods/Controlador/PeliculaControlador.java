package com.distrital.cinedistrito.Controlador;

import com.distrital.cinedistrito.Entity.Pelicula;
import com.distrital.cinedistrito.Entity.Sede;
import com.distrital.cinedistrito.Services.PeliculaService;
import com.distrital.cinedistrito.Services.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pelicula")
@CrossOrigin(origins = "*")
public class PeliculaControlador {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/listar")
    public List<Pelicula> getRecords(){
        return peliculaService.getAll();
    }

}
