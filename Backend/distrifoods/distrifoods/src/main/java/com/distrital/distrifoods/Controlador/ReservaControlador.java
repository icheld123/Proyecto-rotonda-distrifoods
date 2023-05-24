package com.distrital.cinedistrito.Controlador;

import com.distrital.cinedistrito.Entity.Reserva;
import com.distrital.cinedistrito.Modelos.ReservaResponse;
import com.distrital.cinedistrito.Services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("reserva")
@CrossOrigin(origins = "*")
public class ReservaControlador {
    @Autowired
    private ReservaService reservaService;

    @GetMapping("/listar")
    public List<ReservaResponse> getReservas(){
        return reservaService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Reserva save(@RequestBody Reserva reserva){
        return reservaService.save(reserva);
    }
//    @GetMapping("/{id-prestamo}")
//    public Optional<Usuario> getById(@PathVariable("id-prestamo")int usuario){
//        return usuarioService.getUser(usuario);
//    }
}
