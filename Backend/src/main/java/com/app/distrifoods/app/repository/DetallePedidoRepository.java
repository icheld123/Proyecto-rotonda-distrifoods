/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.DetallePedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.app.distrifoods.app.repository.crud.DetallePedidoCrudRepository;

@Repository
public class DetallePedidoRepository {
    @Autowired
    private DetallePedidoCrudRepository detallePedidoCrudRepository;
    
    public List<DetallePedido> getAll() {
        return (List<DetallePedido>) detallePedidoCrudRepository.findAll();
    }
    
    public List<DetallePedido> saveAll(List<DetallePedido> detallePedido) {
        return (List<DetallePedido>) detallePedidoCrudRepository.saveAll(detallePedido);
    }
    
    public DetallePedido save(DetallePedido detallePedido) {
        return detallePedidoCrudRepository.save(detallePedido);
    }
    
    public boolean existId(int id){
        return detallePedidoCrudRepository.existsById(id);
    }
    
    public Optional<DetallePedido> getDetallePedido(int id){
        return detallePedidoCrudRepository.findById(id);
    }
    
    public void delete(DetallePedido detallePedido) {
        detallePedidoCrudRepository.delete(detallePedido);
    }
}
