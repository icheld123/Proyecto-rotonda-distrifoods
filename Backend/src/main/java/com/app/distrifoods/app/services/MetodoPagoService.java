/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.MetodoPago;
import com.app.distrifoods.app.repository.MetodoPagoRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoService {
    @Autowired
    private MetodoPagoRepository repository;
    
    public List<MetodoPago> getAll() {
        return repository.getAll();
    }
    
    public Optional<MetodoPago> getMetodoPago(int id) {
        return repository.getMetodoPago(id);
    }
    
    public boolean existId(int id){
        return repository.existId(id);
    }
    
    public MetodoPago save(MetodoPago metodoPago) {
        boolean entradasCorrectas = !metodoPago.getNombre().isEmpty();
        
        if (metodoPago.getId() == null && entradasCorrectas) {
            return repository.save(metodoPago);
        } 
        else if (metodoPago.getId() != null && entradasCorrectas){
            if (repository.existId(metodoPago.getId())) {
                return metodoPago;
            } else {
                return repository.save(metodoPago);
            }
        }
        else {
            return metodoPago;
        }
    }
    
    public MetodoPago update(MetodoPago metodoPago) {
        if (metodoPago.getId() != null) {
            if (repository.existId(metodoPago.getId())) {
                Optional<MetodoPago> resultado = repository.getMetodoPago(metodoPago.getId());
                if (metodoPago.getNombre() != null && !metodoPago.getNombre().isEmpty()) {
                    resultado.get().setNombre(metodoPago.getNombre());
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return metodoPago;
            }
        } else {
            return metodoPago;
        }
    }
    
    public boolean deleteMetodoPago(int id) {
        Boolean aBoolean = getMetodoPago(id).map(metodoPago -> {
            repository.delete(metodoPago);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
