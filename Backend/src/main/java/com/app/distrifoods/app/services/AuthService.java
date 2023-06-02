/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.dto.LoginRequest;
import com.app.distrifoods.app.dto.LoginResponse;
import com.app.distrifoods.app.entities.Cliente;
import com.app.distrifoods.app.entities.Usuario;
import com.app.distrifoods.app.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService{
    
    final String userAdmin = "admin";
    final String passAdmin = "Admin123$";
    final String userClient = "client";
    final String passClient = "Client123$";
    
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;
    
    public LoginResponse autenticarUser(LoginRequest loginRequest){
    
        LoginResponse loginResponse = new LoginResponse();
        
        Optional<Usuario> usuario = usuarioService.getUsuarioByIdentificacion(loginRequest.getIdentificacion());
        
        if (usuario != null){
            Optional<Cliente> cliente = clienteService.getClienteByIdUsuario(usuario.get().getId());
            //ES UN CLIENTE           
            if(usuario.get().getContrasena().equals(loginRequest.getContrasena())){
                if (cliente.isPresent() && cliente.get() != null && cliente.get().getId() > 0){
                    System.out.println("El usuario es un CLIENTE");
                    loginResponse.setCliente(cliente.get());
                    loginResponse.setIdentificacion(usuario.get().getIdentificacion());
                    loginResponse.setNombre(usuario.get().getNombre());
                    loginResponse.setUsuario(userClient);
                    loginResponse.setContrasena(passClient);
                }
                else {
                    System.out.println("El usuario es un ADMIN");
                    loginResponse.setIdentificacion(usuario.get().getIdentificacion());
                    loginResponse.setNombre(usuario.get().getNombre());
                    loginResponse.setUsuario(userAdmin);
                    loginResponse.setContrasena(passAdmin);
                }
                
            }
        }
        return loginResponse;
    }
}



//@Service
//public class UserDetailsServiceImpl implements UserDetailsService{
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String identificacion) throws UsernameNotFoundException {
//        
//        System.out.println(identificacion);
//        Optional<Usuario> usuario = usuarioRepository.getUsuarioByIdentificacion(Long.parseLong(identificacion, 10)  );
//        if (usuario == null) {
//            throw new UsernameNotFoundException("Usuario no encontrado");
//        }
//       return new org.springframework.security.core.userdetails.User(
//                usuario.get().getIdentificacion().toString(),
//                usuario.get().getContrasena(),
//                new ArrayList<>()
//        );
//    }
//}
