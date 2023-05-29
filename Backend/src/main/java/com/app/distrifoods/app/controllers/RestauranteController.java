/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.controllers;

import com.app.distrifoods.app.entities.Restaurante;
import com.app.distrifoods.app.services.RestauranteService;
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
/**
 *
 * @author jandr
 */

@RestController
@RequestMapping("restaurante")
@CrossOrigin(origins = "*")
public class RestauranteController {
    @Autowired
    private RestauranteService service;
    
    @GetMapping("/all")
    public List<Restaurante> getAll(){
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Restaurante> getRestaurante(@PathVariable("id")int id){
        return service.getRestaurante(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Restaurante save(@RequestBody Restaurante restaurante){
        return service.save(restaurante);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Restaurante update(@RequestBody Restaurante restaurante){
        return service.update(restaurante);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Anotacion que retorna el status
    public boolean delete(@PathVariable("id")int id){
        return service.deleteRestaurante(id);
    }
}
