package com.distrital.cinedistrito.Repository;
import com.distrital.cinedistrito.Entity.Pelicula;
import com.distrital.cinedistrito.Entity.Sala;
import com.distrital.cinedistrito.Entity.Silla;
import com.distrital.cinedistrito.Entity.Usuario;
import com.distrital.cinedistrito.Repository.CRUDRepository.PeliculaCrudRepository;
import com.distrital.cinedistrito.Repository.CRUDRepository.SalaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SalaRepository {

    @Autowired
    private SalaCrudRepository salaCrudRepository;

    public List<Sala> getAll() {
        return (List<Sala>) salaCrudRepository.findAll();
    }

    public Optional<Sala> getSala(int id){
        return salaCrudRepository.findById(id);
    }

    public void save(Sala sala) {
        salaCrudRepository.save(sala);
    }

    public List<Sala> getSalaBySede(int sede) {
        return (List<Sala>) salaCrudRepository.findAllByIdSede(sede);
    }

}