/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Credito;
import com.app.distrifoods.app.repository.CreditoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreditoService {
    @Autowired
    private CreditoRepository repository;

    public List<Credito> getAll() {
        return repository.getAll();
    }
    
    public Optional<Credito> getCredito(int id) {
        return repository.getCredito(id);
    }
    
    public Optional<Credito> getByIdCliente(int id) {
        return repository.getByIdCliente(id);
    }
    
    public Credito save(Credito credito) {
        boolean entradasCorrectas = !credito.getCantidad().toString().isEmpty() && credito.getCantidad() > 0;
        
        if (credito.getId() == null && entradasCorrectas) {
            return repository.save(credito);
        } 
        else if (credito.getId() != null && entradasCorrectas){
            if (repository.existId(credito.getId())) {
                return credito;
            } else {
                return repository.save(credito);
            }
        }
        else {
            return credito;
        }
    }
    
    public Credito update(Credito credito) {
        if (credito.getId() != null) {
            if (repository.existId(credito.getId())) {
                Optional<Credito> resultado = repository.getCredito(credito.getId());
                if (credito.getIdCliente() != null && credito.getIdCliente() > 0) {
                    resultado.get().setIdCliente(credito.getIdCliente());
                }
                if (credito.getCantidad() != null && credito.getCantidad() > 0) {
                    resultado.get().setCantidad(credito.getCantidad());
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return credito;
            }
        } else {
            return credito;
        }
    }
    
    public Boolean delete(int id) {
        Boolean aBoolean = getCredito(id).map(metodoPago -> {
            repository.delete(metodoPago);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
