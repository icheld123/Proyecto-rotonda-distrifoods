/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.dto.LoginRequest;
import com.app.distrifoods.app.entities.Usuario;
import com.app.distrifoods.app.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    
    public final int TAMANO_MIN_CONTRASENA = 8; 
    public final int TAMANO_MIN_ID = 6;
    
    public List<Usuario> getAll() {
        return repository.getAll();
    }
    
    public Optional<Usuario> getUsuario(int id) {
        return repository.getUsuario(id);
    }
    
    public Optional<Usuario> getUsuarioByIdentificacion(long identificacion) {
        return repository.getUsuarioByIdentificacion(identificacion);
    }
    
    public boolean existId(int id){
        return repository.existId(id);
    }
    
    public Usuario save(Usuario usuario) {
        boolean entradasCorrectas = !usuario.getNombre().isEmpty() && !usuario.getContrasena().isEmpty()
                && contrasenaIsValid(usuario.getContrasena()) 
                && usuario.getIdentificacion().toString().length() > TAMANO_MIN_ID 
                && usuario.getContrasena().length() >= TAMANO_MIN_CONTRASENA;
        
        if (usuario.getId() == null && entradasCorrectas) {
            return repository.save(usuario);
        } 
        else if (usuario.getId() != null && entradasCorrectas){
            if (repository.existId(usuario.getId())) {
                return usuario;
            } else {
                return repository.save(usuario);
            }
        }
        else {
            return usuario;
        }
    }
    
    public boolean contrasenaIsValid(String contrasena){
        
        return true;
    }
    
    public Usuario update(Usuario usuario) {
        if (usuario.getId() != null) {
            if (repository.existId(usuario.getId())) {
                Optional<Usuario> resultado = repository.getUsuario(usuario.getId());
                if (usuario.getNombre() != null && !usuario.getNombre().isEmpty()) {
                    resultado.get().setNombre(usuario.getNombre());
                }
                if (usuario.getIdentificacion() != null && usuario.getIdentificacion() > 0 
                        && usuario.getIdentificacion().toString().length() > TAMANO_MIN_ID ) {
                    resultado.get().setIdentificacion(usuario.getIdentificacion());
                }
                if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty() 
                        && usuario.getContrasena().length() >= TAMANO_MIN_CONTRASENA ) {
                    resultado.get().setContrasena(usuario.getContrasena());
                }
                if (usuario.getCorreo() != null && !usuario.getCorreo().isEmpty() && usuario.getCorreo().contains("@")) {
                    resultado.get().setCorreo(usuario.getCorreo());
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return usuario;
            }
        } else {
            return usuario;
        }
    }
    
    public boolean deleteUsuario(int id) {
        Boolean aBoolean = getUsuario(id).map(usuario -> {
            repository.delete(usuario);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
