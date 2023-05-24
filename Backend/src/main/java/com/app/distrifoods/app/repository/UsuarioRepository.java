/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.Usuario;
import com.app.distrifoods.app.repository.crud.UsuarioCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UsuarioRepository {
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;
    
    public List<Usuario> getAll() {
        return (List<Usuario>) usuarioCrudRepository.findAll();
    }
    
    public Usuario save(Usuario usuario) {
        return usuarioCrudRepository.save(usuario);
    }
    
    public boolean existId(int id){
        return usuarioCrudRepository.existsById(id);
    }
    
    public Optional<Usuario> getUsuario(int id){
        return usuarioCrudRepository.findById(id);
    }
    
    public void delete(Usuario usuario) {
        usuarioCrudRepository.delete(usuario);
    }
}
