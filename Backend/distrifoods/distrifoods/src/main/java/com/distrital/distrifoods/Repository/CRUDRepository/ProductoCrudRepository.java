package com.distrital.cinedistrito.Repository.CRUDRepository;
import com.distrital.cinedistrito.Entity.Producto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    public List<Producto> findAllByIdProducto(int producto);
}