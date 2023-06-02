
package com.app.distrifoods.app.controllers;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.dto.AdicionDto;
import com.app.distrifoods.app.services.AdicionService;
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
@RequestMapping("adicion")
@CrossOrigin(origins = "*")
public class AdicionController {
    @Autowired
    private AdicionService service;
    
    @GetMapping("/all")
    public List<Adicion> getAll(){
        return service.getAll();
    }
    
    @GetMapping("/byid/{id}")
    public Optional<Adicion> getById(@PathVariable("id")int id){
        return service.getAdicion(id);
    }
    
    @GetMapping("/all/mapeado")
    public List<AdicionDto> getAllMapeado(){
        return service.getAllMapeado();
    }
    
    @GetMapping("/all/byproducto/{id}")
    public List<Adicion> getAllByProducto(@PathVariable("id")int id){
        return service.getAllByProducto(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Adicion save(@RequestBody Adicion adicion){
        return service.save(adicion);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Adicion update(@RequestBody Adicion adicion){
        return service.update(adicion);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Anotacion que retorna el status
    public boolean delete(@PathVariable("id")int id){
        return service.deleteAdicion(id);
    }
}
