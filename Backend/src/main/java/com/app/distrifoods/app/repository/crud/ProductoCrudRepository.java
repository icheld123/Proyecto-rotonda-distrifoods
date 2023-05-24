package com.app.distrifoods.app.repository.crud;

import com.app.distrifoods.app.entities.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jandr
 */
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer>{
    
    public List<Producto> findAllByIdRestaurante(int id);
}
