package com.distrital.cinedistrito.Repository.CRUDRepository;

import com.distrital.cinedistrito.Entity.Sala;
import com.distrital.cinedistrito.Entity.Silla;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SillaCrudRepository extends CrudRepository<Silla, Integer> {

    public List<Silla> findAllByIdSalaAndIdSede(int sala, int sede);
}