/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.repository;

import com.app.distrifoods.app.entities.Producto;
import com.app.distrifoods.app.repository.crud.ProductoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductoRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    
    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }
    
    public List<Producto> getByRestaurant(int id) {
        return (List<Producto>) productoCrudRepository.findAllByIdRestaurante(id);
    }
    
    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);
    }
    
    public boolean existId(int id){
        return productoCrudRepository.existsById(id);
    }
    
    public Optional<Producto> getProducto(int id){
        return productoCrudRepository.findById(id);
    }
    
    public void delete(Producto producto) {
        productoCrudRepository.delete(producto);
    }
}
