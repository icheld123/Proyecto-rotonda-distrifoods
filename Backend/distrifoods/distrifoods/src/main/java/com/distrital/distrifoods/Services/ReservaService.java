package com.distrital.cinedistrito.Services;
import com.distrital.cinedistrito.Entity.*;
import com.distrital.cinedistrito.Modelos.ReservaResponse;
import com.distrital.cinedistrito.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private SedeRepository sedeRepository;
    @Autowired
    private SillaRepository sillaRepository;
    @Autowired
    private SillaService sillaService;
    @Autowired
    private SalaRepository salaRepository;

    public List<ReservaResponse> getAll() {
        return buildReservaResponse(reservaRepository.getAll());
    }

    public List<ReservaResponse> buildReservaResponse(List<Reserva> reserva){
        List<ReservaResponse> reservaResponse = new ArrayList<ReservaResponse>();
        for(Reserva element: reserva){
            Optional<Usuario> usuario = usuarioRepository.getUsuario(element.getIdUsuario());
            Optional<Pelicula> pelicula = peliculaRepository.getPelicula(element.getIdPelicula());
            Optional<Sede> sede = sedeRepository.getSede(element.getIdSede());
            Optional<Silla> silla = sillaRepository.getSilla(element.getIdSilla());
            Optional<Sala> sala = salaRepository.getSala(element.getIdSala());
            reservaResponse.add(new ReservaResponse(element.getId(),silla.get().getNumSilla(), pelicula.get().getNombrePelicula(), sala.get().getId(), usuario.get().getCedula(), usuario.get().getNombre(),  sede.get().getNombreSede()));
        }
        return reservaResponse;
    }

    public List<Reserva> getReservaByUser(int id_usuario) {
        return reservaRepository.getReservaByUser(id_usuario);
    }


    public Optional<Reserva> getUsuario(int idReserva) {
        return reservaRepository.getReserva(idReserva);
    }

    public Reserva save(Reserva reserva) {
        if (reserva.getId() == null) {
            Optional<Silla> silla = sillaService.getSilla(reserva.getIdSilla());
            if (silla.isPresent() && silla.get().getIdSala() == reserva.getIdSala() && silla.get().getIdSede() == reserva.getIdSede()) {
                silla.get().setEstadoSilla(true);
                sillaService.update(silla.get());
                return reservaRepository.save(reserva);
            }
            else{
                return reserva;
            }
        } else {
            Optional<Reserva> resultado = reservaRepository.getReserva(reserva.getId());
            if (resultado.isPresent()) {
                return reserva;
            } else {
                return reservaRepository.save(reserva);
            }
        }
    }

}
