/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.Estado;
import com.app.distrifoods.app.repository.crud.EstadoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class EstadoRepository {
    @Autowired
    private EstadoCrudRepository estadoCrudRepository;
    
    public List<Estado> getAll() {
        return (List<Estado>) estadoCrudRepository.findAll();
    }
    
    public Estado save(Estado estado) {
        return estadoCrudRepository.save(estado);
    }
    
    public boolean existId(int id){
        return estadoCrudRepository.existsById(id);
    }
    
    public Optional<Estado> getEstado(int id){
        return estadoCrudRepository.findById(id);
    }
    
    public void delete(Estado estado) {
        estadoCrudRepository.delete(estado);
    }
}
