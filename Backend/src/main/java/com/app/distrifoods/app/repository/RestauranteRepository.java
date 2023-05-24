/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.Restaurante;
import com.app.distrifoods.app.repository.crud.RestauranteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author jandr
 */

@Repository
public class RestauranteRepository {
    @Autowired
    private RestauranteCrudRepository restauranteCrudRepository;
    
    public List<Restaurante> getAll() {
        return (List<Restaurante>) restauranteCrudRepository.findAll();
    }
    
    public Restaurante save(Restaurante restaurante) {
        return restauranteCrudRepository.save(restaurante);
    }
    
    public Optional<Restaurante> getRestaurante(int id){
        return restauranteCrudRepository.findById(id);
    }
    
    public boolean existId(int id){
        return restauranteCrudRepository.existsById(id);
    }
    
    public void delete(Restaurante restaurante) {
        restauranteCrudRepository.delete(restaurante);
    }
}
