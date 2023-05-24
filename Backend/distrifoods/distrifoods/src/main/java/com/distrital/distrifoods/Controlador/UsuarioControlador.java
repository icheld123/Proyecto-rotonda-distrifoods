package com.distrital.cinedistrito.Controlador;

import com.distrital.cinedistrito.Entity.Usuario;
import com.distrital.cinedistrito.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "*")
public class UsuarioControlador {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> getRecords(){
        return usuarioService.getAll();
    }

    @GetMapping("/{cedula}")
    public int getUser(@PathVariable("cedula")Long cedula){
        return usuarioService.getUserByCedula(cedula);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Usuario save(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Usuario update(@RequestBody Usuario usuario){
        return usuarioService.update(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Anotacion que retorna el status
    public boolean delete(@PathVariable("id")int usuarioId){
        return usuarioService.deleteClient(usuarioId);
    }

//    @GetMapping("/{id-prestamo}")
//    public Optional<Usuario> getById(@PathVariable("id-prestamo")int usuario){
//        return usuarioService.getUser(usuario);
//    }

}
