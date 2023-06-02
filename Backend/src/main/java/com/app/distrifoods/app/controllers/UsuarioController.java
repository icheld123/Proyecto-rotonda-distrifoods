/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.controllers;

import com.app.distrifoods.app.dto.LoginRequest;
import com.app.distrifoods.app.dto.LoginResponse;
import com.app.distrifoods.app.entities.Usuario;
import com.app.distrifoods.app.services.AuthService;
import com.app.distrifoods.app.services.UsuarioService;
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
@RequestMapping("usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    
    @GetMapping("/all")
    public List<Usuario> getAll(){
        return service.getAll();
    }
    
    @GetMapping("/all/byidentificacion/{id}")
    public Optional<Usuario> getAll(@PathVariable("id")int id){
        return service.getUsuarioByIdentificacion(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Usuario save(@RequestBody Usuario usuario){
        return service.save(usuario);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Usuario update(@RequestBody Usuario usuario){
        return service.update(usuario);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Anotacion que retorna el status
    public boolean delete(@PathVariable("id")int id){
        return service.deleteUsuario(id);
    }
}
