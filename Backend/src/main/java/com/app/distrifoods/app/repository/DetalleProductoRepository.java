/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.DetalleProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.app.distrifoods.app.repository.crud.DetalleProductoCrudRepository;

@Repository
public class DetalleProductoRepository {
    @Autowired
    private DetalleProductoCrudRepository detalleProductoCrudRepository;
    
    public List<DetalleProducto> getAll() {
        return (List<DetalleProducto>) detalleProductoCrudRepository.findAll();
    }
    
    public List<DetalleProducto> getAllByIdPedido(int id) {
        return (List<DetalleProducto>) detalleProductoCrudRepository.findAllByIdPedido(id);
    }
    
    public List<DetalleProducto> saveAll(List<DetalleProducto> detalleProducto) {
        return (List<DetalleProducto>) detalleProductoCrudRepository.saveAll(detalleProducto);
    }
    
    public DetalleProducto save(DetalleProducto detalleProducto) {
        return detalleProductoCrudRepository.save(detalleProducto);
    }
    
    public boolean existId(int id){
        return detalleProductoCrudRepository.existsById(id);
    }
    
//    public boolean existByIdPedido(int id){
//        return detalleProductoCrudRepository.existByIdPedido(id);
//    }
    
    public Optional<DetalleProducto> getDetalleProducto(int id){
        return detalleProductoCrudRepository.findById(id);
    }
    
    public void delete(DetalleProducto detalleProducto) {
        detalleProductoCrudRepository.delete(detalleProducto);
    }
}
