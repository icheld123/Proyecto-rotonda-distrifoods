package com.distrital.cinedistrito.Services;
import com.distrital.cinedistrito.Entity.Silla;
import com.distrital.cinedistrito.Repository.SillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SillaService {

    final int CANTIDAD_SILLAS = 60;
    final String[] TIPO_SILLA = {"General","Preferencial"};

    @Autowired
    private SillaRepository sillaRepository;

    public List<Silla> getAll() {
        return sillaRepository.getAll();
    }

    public Optional<Silla> getSilla(int id) {
        return sillaRepository.getSilla(id);
    }


    public List<Silla> getSillaBySalaAndSede(int sala, int sede) {
        return sillaRepository.getSillaBySalaAndSede(sala, sede);
    }

    public void crearSilla(int idSala, int idSede){
        for (int j = 0; j < CANTIDAD_SILLAS; j++) {
            Silla silla = new Silla(null, idSala, j+1, (j <= 39) ? TIPO_SILLA[0] : TIPO_SILLA[1],false,idSede);
            save(silla);
        }
    }

    public void save(Silla silla) {
        sillaRepository.save(silla);
    }

    public Silla update(Silla silla) {
        if (silla.getId() != null) {
            Optional<Silla> resultado = sillaRepository.getSilla(silla.getId());
            if (resultado.isPresent()) {
                resultado.get().setEstadoSilla(silla.getEstadoSilla());
                sillaRepository.save(resultado.get());
                return resultado.get();
            } else {
                return silla;
            }
        } else {
            return silla;
        }
    }
}