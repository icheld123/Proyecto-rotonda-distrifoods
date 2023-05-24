package com.distrital.cinedistrito.Controlador;

import com.distrital.cinedistrito.Entity.Producto;
import com.distrital.cinedistrito.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empleado")
@CrossOrigin(origins = "*")
public class EmpleadoControlador {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> getRecords(){
        return productoService.getAll();
    }

    @GetMapping("/{id}")
    public int getProducto(@PathVariable("id")int id){
        return productoService.getProductoById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Producto save(@RequestBody Producto producto){
        return productoService.save(producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Anotacion que retorna el status
    public boolean delete(@PathVariable("id")int id){
        return productoService.deleteEmpleado(id);
    }
}
