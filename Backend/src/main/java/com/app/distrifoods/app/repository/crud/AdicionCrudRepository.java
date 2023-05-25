package com.app.distrifoods.app.repository.crud;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.entities.dto.AdicionDto;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jandr
 */
public interface AdicionCrudRepository extends CrudRepository<Adicion, Integer>{
    
    public List<Adicion> findAllByIdProducto(int id);
    
}
