package com.app.distrifoods.app.repository;


import com.app.distrifoods.app.entities.Sucursal;
import com.app.distrifoods.app.repository.crud.SucursalCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public class SucursalRepository {
    @Autowired
    private SucursalCrudRepository sucursalCrudRepository;
    
    public List<Sucursal> getAll() {
        return (List<Sucursal>) sucursalCrudRepository.findAll();
    }
    
    public Sucursal save(Sucursal sucursal) {
        return sucursalCrudRepository.save(sucursal);
    }
    
    public boolean existId(int id){
        return sucursalCrudRepository.existsById(id);
    }
    
    public Optional<Sucursal> getSucursal(int id){
        return sucursalCrudRepository.findById(id);
    }
    
    public void delete(Sucursal sucursal) {
        sucursalCrudRepository.delete(sucursal);
    }
}
