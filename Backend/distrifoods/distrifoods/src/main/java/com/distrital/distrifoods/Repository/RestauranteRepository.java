package com.distrital.cinedistrito.Repository;
import com.distrital.cinedistrito.Entity.Restaurante;
import com.distrital.cinedistrito.Repository.CRUDRepository.RestauranteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestauranteRepository {

    @Autowired
    private RestauranteCrudRepository restauranteCrudRepository;

    public List<Restaurante> getAll() {
        return (List<Restaurante>) restauranteCrudRepository.findAll();
    }
    public void save(Restaurante restaurante) {
        restauranteCrudRepository.save(restaurante);
    }
    public Optional<Restaurante> getRestaurante(int id){
        return restauranteCrudRepository.findById(id);
    }

}