package com.distrital.cinedistrito.Controlador;

import com.distrital.cinedistrito.Entity.Sede;
import com.distrital.cinedistrito.Entity.Silla;
import com.distrital.cinedistrito.Services.SedeService;
import com.distrital.cinedistrito.Services.SillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("silla")
@CrossOrigin(origins = "*")
public class SillaControlador {

    @Autowired
    private SillaService sillaService;

    @GetMapping("/listar")
    public List<Silla> getRecords(){
        return sillaService.getAll();
    }

    @GetMapping("/{sala}/{sede}")
    public List<Silla> getById(@PathVariable("sala")int sala, @PathVariable("sede")int sede){
        return sillaService.getSillaBySalaAndSede(sala, sede);
    }
}
