package com.distrital.cinedistrito.Repository;
import com.distrital.cinedistrito.Entity.Silla;
import com.distrital.cinedistrito.Repository.CRUDRepository.SillaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SillaRepository {

    @Autowired
    private SillaCrudRepository sillaCrudRepository;

    public List<Silla> getAll() {
        return (List<Silla>) sillaCrudRepository.findAll();
    }

    public Optional<Silla> getSilla(int id){
        return sillaCrudRepository.findById(id);
    }

    public Silla save(Silla silla) {
        return sillaCrudRepository.save(silla);
    }

    public List<Silla> getSillaBySalaAndSede(int sala, int sede) {
        return sillaCrudRepository.findAllByIdSalaAndIdSede(sala, sede);
    }
}