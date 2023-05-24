package com.distrital.cinedistrito.Repository;
import com.distrital.cinedistrito.Entity.Usuario;
import com.distrital.cinedistrito.Repository.CRUDRepository.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public Optional<Usuario> findAllByCedula(Long cedula) {
        return usuarioCrudRepository.findAllByCedula(cedula);
    }

    public Optional<Usuario> getUsuario(int usuarioId){
        return usuarioCrudRepository.findById(usuarioId);
    }

    public void delete(Usuario usuario) {
        usuarioCrudRepository.delete(usuario);
    }
}