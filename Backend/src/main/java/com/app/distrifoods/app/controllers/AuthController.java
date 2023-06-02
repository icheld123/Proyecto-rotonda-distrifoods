/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.controllers;

import com.app.distrifoods.app.dto.LoginRequest;
import com.app.distrifoods.app.dto.LoginResponse;
import com.app.distrifoods.app.services.AuthService;
//import com.app.distrifoods.app.services.UsuarioService;
//import javax.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthService service;
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        return service.autenticarUser(loginRequest);
    }
    
    
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    
//    
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//        // Autenticar al usuario usando el AuthenticationManager
//        try {
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    loginRequest.getIdentificacion().toString(),
//                    loginRequest.getContrasena()
//            );
//            
//            // Realizar la autenticacion
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            
//            // Establecer la autenticacion en el contexto de seguridad
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Devolver una respuesta de exito al cliente
//            return ResponseEntity.ok().build();
//        } catch (AuthenticationException e) {
//            // Manejo de autenticacion fallida
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        
//    }
    
}
