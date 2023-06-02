/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.Cliente;
import com.app.distrifoods.app.repository.crud.ClienteCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository {
    @Autowired
    private ClienteCrudRepository clienteCrudRepository;
    
    public List<Cliente> getAll() {
        return (List<Cliente>) clienteCrudRepository.findAll();
    }
    
    public Cliente save(Cliente cliente) {
        return clienteCrudRepository.save(cliente);
    }
    
    public boolean existId(int id){
        return clienteCrudRepository.existsById(id);
    }
    
    public Optional<Cliente> getCliente(int id){
        return clienteCrudRepository.findById(id);
    }
    
    public Optional<Cliente> getClienteByIdUsuario(int id){
        return clienteCrudRepository.findByIdUsuario(id);
    }
    
    public void delete(Cliente cliente) {
        clienteCrudRepository.delete(cliente);
    }
}
