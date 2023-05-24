/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.TipoProducto;
import com.app.distrifoods.app.repository.crud.TipoProductoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class TipoProductoRepository {
    @Autowired
    private TipoProductoCrudRepository tipoProductoCrudRepository;
    
    public List<TipoProducto> getAll() {
        return (List<TipoProducto>) tipoProductoCrudRepository.findAll();
    }
    
    public TipoProducto save(TipoProducto tipoProducto) {
        return tipoProductoCrudRepository.save(tipoProducto);
    }
    
    public boolean existId(int id){
        return tipoProductoCrudRepository.existsById(id);
    }
    
    public Optional<TipoProducto> getTipoProducto(int id){
        return tipoProductoCrudRepository.findById(id);
    }
    
    public void delete(TipoProducto tipoProducto) {
        tipoProductoCrudRepository.delete(tipoProducto);
    }
}
