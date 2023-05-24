package com.distrital.cinedistrito.Services;
import com.distrital.cinedistrito.Entity.Horario;
import com.distrital.cinedistrito.Repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Time;
import java.util.List;

@Service
public class HorarioService {
    final int CANTIDAD_SEDES = 6;
    final int CANTIDAD_SALAS = 6;
    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> getAll() {
        return horarioRepository.getAll();
    }
    public void crearHorarios(){
        for(int i = 0; i<CANTIDAD_SEDES; i++){
            for(int j = 0; j<CANTIDAD_SALAS; j++){
                long rndInt = (long) (Math.floor(Math.random() * 72000000) + 28800000);
                Time time = new Time(rndInt);
                Horario horario = new Horario(null,time.toString(),j+1);
                System.out.println(time.toString());
                save(horario);
            }
        }
    }
    public void save(Horario horario) {
        horarioRepository.save(horario);
    }
}
