package com.distrital.cinedistrito.Controlador;

import com.distrital.cinedistrito.Entity.Pelicula;
import com.distrital.cinedistrito.Entity.Sala;
import com.distrital.cinedistrito.Entity.Silla;
import com.distrital.cinedistrito.Services.PeliculaService;
import com.distrital.cinedistrito.Services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sala")
@CrossOrigin(origins = "*")
public class SalaControlador {

    @Autowired
    private SalaService salaService;

    @GetMapping("/listar")
    public List<Sala> getRecords(){
        return salaService.getAll();
    }

    @GetMapping("/{sede}")
    public List<Sala> getById(@PathVariable("sede")int sede){
        return salaService.getSalaBySede(sede);
    }

    //solamente para crear los primeros registros
    @GetMapping("/crear")
    public void crearSala(){
        salaService.crearSala();
    }

}
