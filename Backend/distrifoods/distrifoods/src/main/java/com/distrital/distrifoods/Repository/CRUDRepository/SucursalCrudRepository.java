package com.distrital.cinedistrito.Repository.CRUDRepository;
import com.distrital.cinedistrito.Entity.Sucursal;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SucursalCrudRepository extends CrudRepository<Sucursal, Integer> {

    public List<Sucursal> findAllByIdSucursal(int sucursal);
}