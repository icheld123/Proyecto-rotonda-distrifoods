/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Sucursal;
import com.app.distrifoods.app.repository.SucursalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SucursalService {
    
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private SucursalRepository repository;
    
    
    public List<Sucursal> getAll() {
        return repository.getAll();
    }
    
    public Optional<Sucursal> getSucursal(int id) {
        return repository.getSucursal(id);
    }
    
    public Sucursal save(Sucursal sucursal) {
        
        boolean existeRestaurante = restauranteService.existId(sucursal.getId_restaurante());
        boolean entradasCorrectas = !sucursal.getDireccion().isEmpty();
        
        if (sucursal.getId() == null && existeRestaurante && entradasCorrectas) {
            return repository.save(sucursal);
        } 
        else if (sucursal.getId() != null && existeRestaurante  && entradasCorrectas) {
            if (repository.existId(sucursal.getId())){
                return sucursal;
            }
            else {
                return repository.save(sucursal);
            }
        } 
        else {
            return sucursal;
        }
    }
    
    public Sucursal update(Sucursal sucursal) {
        
        if (sucursal.getId() != null) {
            if (repository.existId(sucursal.getId())) {
                Optional<Sucursal> resultado = repository.getSucursal(sucursal.getId());
                if (sucursal.getDireccion() != null && !sucursal.getDireccion().isEmpty()) {
                    resultado.get().setDireccion(sucursal.getDireccion());
                }
                if (sucursal.getId_restaurante() != null) {
                    if (restauranteService.existId(sucursal.getId_restaurante())){
                        resultado.get().setId_restaurante(sucursal.getId_restaurante());
                    }
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return sucursal;
            }
        } else {
            return sucursal;
        }
    }
    
    public boolean deleteSucursal(int id) {
        Boolean aBoolean = getSucursal(id).map(sucursal -> {
            repository.delete(sucursal);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
