
package com.app.distrifoods.app.controllers;

import com.app.distrifoods.app.entities.Producto;
import com.app.distrifoods.app.entities.dto.ProductoCompletoDto;
import com.app.distrifoods.app.services.ProductoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producto")
@CrossOrigin(origins = "*")
public class ProductoController {
    @Autowired
    private ProductoService service;
    
//    @GetMapping("/all")
//    public List<Producto> getAll(){
//        return service.getAll();
//    }
    
    @GetMapping("/{id}")
    public Optional<Producto> getById(@PathVariable("id")int id){
        return service.getProducto(id);
    }
    
    @GetMapping("/all")
    public List<ProductoCompletoDto> getAll(){
        return service.getAllMapeado();
    }
    
    @GetMapping("/prueba")
    public List<ProductoCompletoDto> prueba(){
        return service.prueba();
    }
    
    @GetMapping("/all/byrestaurant/{id}")
    public List<ProductoCompletoDto> getAllByRestaurant(@PathVariable("id")int id){
        return service.getAllByRestaurant(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Producto save(@RequestBody Producto producto){
        return service.save(producto);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Producto update(@RequestBody Producto producto){
        return service.update(producto);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Anotacion que retorna el status
    public boolean delete(@PathVariable("id")int id){
        return service.deleteProducto(id);
    }
}