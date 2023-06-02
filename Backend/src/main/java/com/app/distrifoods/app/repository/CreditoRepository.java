/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.Cliente;
import com.app.distrifoods.app.entities.Credito;
import com.app.distrifoods.app.entities.Producto;
import com.app.distrifoods.app.repository.crud.ClienteCrudRepository;
import com.app.distrifoods.app.repository.crud.CreditoCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CreditoRepository {
    @Autowired
    private CreditoCrudRepository creditoCrudRepository;
    
    public List<Credito> getAll() {
        return (List<Credito>) creditoCrudRepository.findAll();
    }
    
    public Optional<Credito> getByIdCliente(int id) {
        return creditoCrudRepository.findByIdCliente(id);
    }
    
    public Credito save(Credito credito) {
        return creditoCrudRepository.save(credito);
    }
    
    public Optional<Credito> getCredito(int id){
        return creditoCrudRepository.findById(id);
    }
    
    public void delete(Credito credito) {
        creditoCrudRepository.delete(credito);
    }
    
    public boolean existId(int id){
        return creditoCrudRepository.existsById(id);
    }
}
