/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.controllers;

import com.app.distrifoods.app.entities.Credito;
import com.app.distrifoods.app.services.CreditoService;
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
@RequestMapping("credito")
@CrossOrigin(origins = "*")
public class CreditoController {
    @Autowired
    private CreditoService service;
    
    @GetMapping("/all")
    public List<Credito> getAll(){
        return service.getAll();
    }
    
    @GetMapping("/byidcliente/{id}")
    public Optional<Credito> getCreditoByIdClient(@PathVariable("id")int id){
        return service.getByIdCliente(id);
    }
    
    @GetMapping("/byid/{id}")
    public Optional<Credito> getCredito(@PathVariable("id")int id){
        return service.getCredito(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Credito save(@RequestBody Credito credito){
        return service.save(credito);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Credito update(@RequestBody Credito credito){
        return service.update(credito);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Anotacion que retorna el status
    public boolean delete(@PathVariable("id")int id){
        return service.delete(id);
    }
}
