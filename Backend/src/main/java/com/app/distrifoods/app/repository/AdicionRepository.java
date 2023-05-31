/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.repository.crud.AdicionCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public class AdicionRepository {
    @Autowired
    private AdicionCrudRepository adicionCrudRepository;
    
    public List<Adicion> getAll() {
        return (List<Adicion>) adicionCrudRepository.findAll();
    }
    
    public List<Adicion> getByProducto(int id) {
        return (List<Adicion>) adicionCrudRepository.findAllByIdProducto(id);
    }
    
    public Adicion save(Adicion adicion) {
        return adicionCrudRepository.save(adicion);
    }
    
    public boolean existId(int id){
        return adicionCrudRepository.existsById(id);
    }
    
    public Optional<Adicion> getAdicion(int id){
        return adicionCrudRepository.findById(id);
    }
    
    public void delete(Adicion adicion) {
        adicionCrudRepository.delete(adicion);
    }
}
