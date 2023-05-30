/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.distrifoods.app.repository.crud;

import com.app.distrifoods.app.entities.DetallePedido;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jandr
 */
public interface DetallePedidoCrudRepository extends CrudRepository<DetallePedido, Integer>{
    
    List<DetallePedido> findAllByIdPedido(int id);
}
