package com.distrital.cinedistrito.Repository;
import com.distrital.cinedistrito.Entity.Sucursal;
import com.distrital.cinedistrito.Repository.CRUDRepository.SucursalCrudRepository;
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

    public Optional<Sucursal> getSede(int sedeId){
        return sucursalCrudRepository.findById(sedeId);
    }

    public void save(Sucursal sucursal) {
        sucursalCrudRepository.save(sucursal);
    }

}