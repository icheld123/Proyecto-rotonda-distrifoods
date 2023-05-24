/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Restaurante;
import com.app.distrifoods.app.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
/**
 *
 * @author jandr
 */
@Service
public class RestauranteService {
    
    @Autowired
    private RestauranteRepository repository;
    
    public List<Restaurante> getAll() {
        return repository.getAll();
    }
    
    public Optional<Restaurante> getRestaurante(int id) {
        return repository.getRestaurante(id);
    }
    
    public boolean existId(int id){
        return repository.existId(id);
    }
    
    public Restaurante save(Restaurante restaurante) {
        boolean entradasCorrectas = !restaurante.getNombre().isEmpty() && !restaurante.getUrlLogo().isEmpty();
        
        if (restaurante.getId()== null && entradasCorrectas) {
            return repository.save(restaurante);
        } 
        else if (restaurante.getId() != null && entradasCorrectas) {
            if (repository.existId(restaurante.getId())) {
                return restaurante;
            } else {
                return repository.save(restaurante);
            }
        }
        else {
            return restaurante;
        }
    }
    
    public Restaurante update(Restaurante restaurante) {
        if (restaurante.getId() != null) {
            Optional<Restaurante> resultado = repository.getRestaurante(restaurante.getId());
            if (resultado.isPresent()) {
                if (restaurante.getNombre() != null && !restaurante.getNombre().isEmpty()) {
                    resultado.get().setNombre(restaurante.getNombre());
                }
                if (restaurante.getUrlLogo() != null && !restaurante.getUrlLogo().isEmpty()) {
                    resultado.get().setNombre(restaurante.getNombre());
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return restaurante;
            }
        } else {
            return restaurante;
        }
    }
    
    public boolean deleteRestaurante(int id) {
        Boolean aBoolean = getRestaurante(id).map(restaurante -> {
            repository.delete(restaurante);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
