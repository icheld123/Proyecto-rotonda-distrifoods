package com.distrital.cinedistrito.Services;
import com.distrital.cinedistrito.Entity.Sucursal;
import com.distrital.cinedistrito.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> getAll() {
        return sucursalRepository.getAll();
    }

    public Optional<Sucursal> getSusursal(int id_sucusal) {
        return sucursalRepository.getSede(id_sucusal);
    }

    public void save(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
    }
    public void crearSucursal(String direccion,int id_restaurante){
        Sucursal sucursal = new Sucursal(null, direccion, id_restaurante);
        save(sucursal);
    }
}