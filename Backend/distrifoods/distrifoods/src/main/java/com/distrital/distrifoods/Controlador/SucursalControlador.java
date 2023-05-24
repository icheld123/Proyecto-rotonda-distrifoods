package com.distrital.cinedistrito.Controlador;

import com.distrital.cinedistrito.Entity.Sucursal;
import com.distrital.cinedistrito.Services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sucursal")
@CrossOrigin(origins = "*")
public class SucursalControlador {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("/listar")
    public List<Sucursal> getRecords(){
        return sucursalService.getAll();
    }

}
