/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.Pedido;
import com.app.distrifoods.app.repository.crud.PedidoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public class PedidoRepository {
    @Autowired
    private PedidoCrudRepository pedidoCrudRepository;
    
    public List<Pedido> getAll() {
        return (List<Pedido>) pedidoCrudRepository.findAll();
    }
    
    public Pedido save(Pedido pedido) {
        return pedidoCrudRepository.save(pedido);
    }
    
    public boolean existId(int id){
        return pedidoCrudRepository.existsById(id);
    }
    
    public Optional<Pedido> getPedido(int id){
        return pedidoCrudRepository.findById(id);
    }
    
    public void delete(Pedido pedido) {
        pedidoCrudRepository.delete(pedido);
    }
}
