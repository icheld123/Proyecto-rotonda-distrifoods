package com.distrital.cinedistrito.Services;
import com.distrital.cinedistrito.Entity.Usuario;
import com.distrital.cinedistrito.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.getAll();
    }

    public int getUserByCedula(Long cedula) {
        Optional<Usuario> resultado = usuarioRepository.findAllByCedula(cedula);
        if (resultado.isPresent()) {
            return resultado.get().getId();
        }
        return 0;
    }

    public Optional<Usuario> getUsuario(int usuarioId) {
        return usuarioRepository.getUsuario(usuarioId);
    }


    public Usuario save(Usuario usuario) {
            Optional<Usuario> resultado = usuarioRepository.findAllByCedula(usuario.getCedula());
        if (usuario.getId() == null) {
            if (resultado.isPresent()) {
                return usuario;
            }
            return usuarioRepository.save(usuario);
        } else {
            if (resultado.isPresent()) {
                return usuario;
            } else {
                return usuarioRepository.save(usuario);
            }
        }
    }


    public Usuario update(Usuario usuario) {
        if (usuario.getId() != null) {
            Optional<Usuario> resultado = usuarioRepository.getUsuario(usuario.getId());
            if (resultado.isPresent()) {
                if (usuario.getCedula() != null) {
                    resultado.get().setCedula(usuario.getCedula());
                }
                if (usuario.getCorreo() != null) {
                    resultado.get().setCorreo(usuario.getCorreo());
                }
                if (usuario.getNombre() != null) {
                    resultado.get().setNombre(usuario.getNombre());
                }
                if (usuario.getTelefono() != null) {
                    resultado.get().setTelefono(usuario.getTelefono());
                }
                if (usuario.getApellido() != null) {
                    resultado.get().setApellido(usuario.getApellido());
                }
                usuarioRepository.save(resultado.get());
                return resultado.get();
            } else {
                return usuario;
            }
        } else {
            return usuario;
        }
    }


    public boolean deleteClient(int clientId) {
        Boolean aBoolean = getUsuario(clientId).map(client -> {
            usuarioRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}