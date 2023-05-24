package com.distrital.cinedistrito.Repository;

import com.distrital.cinedistrito.Entity.Producto;
import com.distrital.cinedistrito.Repository.CRUDRepository.ProductoCrudRepository;
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

    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    public Optional<Producto> findAllById(Integer id) {
        return productoCrudRepository.findById(id);
    }

    public Optional<Producto> getProducto(int producto){
        return productoCrudRepository.findById(producto);
    }

    public void delete(Producto producto) {
        productoCrudRepository.delete(producto);
    }
}
