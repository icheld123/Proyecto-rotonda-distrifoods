package com.distrital.cinedistrito.Services;
import com.distrital.cinedistrito.Entity.Pelicula;
import com.distrital.cinedistrito.Entity.Sede;
import com.distrital.cinedistrito.Repository.PeliculaRepository;
import com.distrital.cinedistrito.Repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> getAll() {
        return peliculaRepository.getAll();
    }

    public Optional<Pelicula> getPelicula(int id) {
        return peliculaRepository.getPelicula(id);
    }
}