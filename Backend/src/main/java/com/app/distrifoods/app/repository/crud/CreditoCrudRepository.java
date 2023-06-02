/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.distrifoods.app.repository.crud;

import com.app.distrifoods.app.entities.Credito;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jandr
 */
public interface CreditoCrudRepository extends CrudRepository<Credito, Integer>{
    public Optional<Credito> findByIdCliente(int id);
}
