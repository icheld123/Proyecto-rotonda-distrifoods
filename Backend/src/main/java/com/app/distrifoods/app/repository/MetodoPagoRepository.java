/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.MetodoPago;
import com.app.distrifoods.app.repository.crud.MetodoPagoCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MetodoPagoRepository {
    @Autowired
    private MetodoPagoCrudRepository metodoPagoCrudRepository;
    
    public List<MetodoPago> getAll() {
        return (List<MetodoPago>) metodoPagoCrudRepository.findAll();
    }
    
    public MetodoPago save(MetodoPago metodoPago) {
        return metodoPagoCrudRepository.save(metodoPago);
    }
    
    public boolean existId(int id){
        return metodoPagoCrudRepository.existsById(id);
    }
    
    public Optional<MetodoPago> getMetodoPago(int id){
        return metodoPagoCrudRepository.findById(id);
    }
    
    public void delete(MetodoPago metodoPago) {
        metodoPagoCrudRepository.delete(metodoPago);
    }
}
