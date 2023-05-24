package com.distrital.cinedistrito.Repository;
import com.distrital.cinedistrito.Entity.Horario;
import com.distrital.cinedistrito.Repository.CRUDRepository.HorarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HorarioRepository {
    @Autowired
    private HorarioCrudRepository horarioCrudRepository;

    public List<Horario> getAll() {
        return (List<Horario>) horarioCrudRepository.findAll();
    }
    public void save(Horario horario) {
        horarioCrudRepository.save(horario);
    }
}
