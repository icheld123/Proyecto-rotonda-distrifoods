package com.distrital.cinedistrito.Controlador;
import com.distrital.cinedistrito.Entity.Horario;
import com.distrital.cinedistrito.Services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("horario")
@Component
@CrossOrigin(origins = "*")
public class HorarioControlador {
    @Autowired
    private HorarioService horarioService;

    @GetMapping("/listar")
    public List<Horario> getHorarios(){
        return horarioService.getAll();
    }

    @PostMapping ("/crear")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public void crearHorarios(){
        horarioService.crearHorarios();
    }
}
