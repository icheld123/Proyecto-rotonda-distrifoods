/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.distrifoods.app.repository.crud;

import com.app.distrifoods.app.entities.Pedido;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jandr
 */
public interface PedidoCrudRepository extends CrudRepository<Pedido, Integer> {
    
}
