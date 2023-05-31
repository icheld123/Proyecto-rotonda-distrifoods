package com.app.distrifoods.app.repository.crud;

import com.app.distrifoods.app.entities.Adicion;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jandr
 */
public interface AdicionCrudRepository extends CrudRepository<Adicion, Integer>{
    
    public List<Adicion> findAllByIdProducto(int id);
    
}
