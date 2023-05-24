package com.distrital.cinedistrito.Repository.CRUDRepository;

import com.distrital.cinedistrito.Entity.Restaurante;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestauranteCrudRepository extends CrudRepository<Restaurante, Integer> {

//    public Optional<Usuario> findAllByCedula(Long cedula);
}