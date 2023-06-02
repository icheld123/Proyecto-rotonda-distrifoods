/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.controllers;

import com.app.distrifoods.app.dto.ClienteDto;
import com.app.distrifoods.app.entities.Cliente;
import com.app.distrifoods.app.services.ClienteService;
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
@RequestMapping("cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    private ClienteService service;
    
    @GetMapping("/all")
    public List<Cliente> getAll(){
        return service.getAll();
    }
    
    @GetMapping("/byidusuario/{id}")
    public Optional<Cliente> getClienteByIdUsuario(@PathVariable("id")int id){
        return service.getClienteByIdUsuario(id);
    }
    
    @GetMapping("/byid/{id}")
    public Optional<Cliente> getClienteById(@PathVariable("id")int id){
        return service.getClienteById(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Cliente save(@RequestBody Cliente cliente){
        return service.save(cliente);
    }
    
    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Cliente registro(@RequestBody ClienteDto clienteDto){
        return service.registro(clienteDto);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Cliente update(@RequestBody Cliente cliente){
        return service.update(cliente);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Anotacion que retorna el status
    public boolean delete(@PathVariable("id")int id){
        return service.deleteCliente(id);
    }
}
