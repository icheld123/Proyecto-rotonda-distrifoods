/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Cliente;
import com.app.distrifoods.app.entities.Credito;
import com.app.distrifoods.app.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CreditoService creditoService;
    
    public final int TAMANO_TELEFONO = 10;
    public final float CREDITOS_REGALO = 30000;
    
    public List<Cliente> getAll() {
        return repository.getAll();
    }
    
    public Optional<Cliente> getCliente(int id) {
        return repository.getCliente(id);
    }
    
    public Optional<Cliente> getClienteByIdUsuario(int id) {
        return repository.getClienteByIdUsuario(id);
    }
    
    public Optional<Cliente> getClienteById(int id) {
        return repository.getCliente(id);
    }
    
    public boolean existId(int id){
        return repository.existId(id);
    }
    
    public Cliente save(Cliente cliente) {
        Cliente clienteGuardado = new Cliente();
        Credito credito = new Credito();
        boolean existeUsuario = usuarioService.existId(cliente.getIdUsuario());
        boolean entradasCorrectas = !cliente.getDireccion().isEmpty() && !cliente.getTelefono().toString().isEmpty()
                && cliente.getTelefono() > 0 && cliente.getTelefono().toString().length() == TAMANO_TELEFONO;
        
        if (cliente.getId() == null && entradasCorrectas && existeUsuario) {
            clienteGuardado = repository.save(cliente);
            credito.setIdCliente(clienteGuardado.getId());
            credito.setCantidad(CREDITOS_REGALO);
            creditoService.save(credito);
            return clienteGuardado;
        } 
        else if (cliente.getId() != null && entradasCorrectas && existeUsuario){
            if (repository.existId(cliente.getId())) {
                return cliente;
            } else {
                clienteGuardado = repository.save(cliente);
                return clienteGuardado;
            }
        }
        else {
            return cliente;
        }
    }
    
    
    public Cliente update(Cliente cliente) {
        if (cliente.getId() != null) {
            if (repository.existId(cliente.getId())) {
                Optional<Cliente> resultado = repository.getCliente(cliente.getId());
                if (cliente.getDireccion() != null && !cliente.getDireccion().isEmpty()) {
                    resultado.get().setDireccion(cliente.getDireccion());
                }
                if (cliente.getTelefono() != null && cliente.getTelefono() > 0 
                        && cliente.getTelefono().toString().length() == TAMANO_TELEFONO) {
                    resultado.get().setTelefono(cliente.getTelefono());
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return cliente;
            }
        } else {
            return cliente;
        }
    }
    
    public boolean deleteCliente(int id) {
        Boolean aBoolean = getCliente(id).map(cliente -> {
            repository.delete(cliente);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
