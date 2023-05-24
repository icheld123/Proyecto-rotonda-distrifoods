package com.distrital.cinedistrito.Repository;

import com.distrital.cinedistrito.Entity.Reserva;
import com.distrital.cinedistrito.Repository.CRUDRepository.ReservaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ReservaRepository {

    @Autowired
    private ReservaCrudRepository reservaCrudRepository;

    public List<Reserva> getAll() {
        return (List<Reserva>) reservaCrudRepository.findAll();
    }

    public Reserva save(Reserva reserva) {
        return reservaCrudRepository.save(reserva);
    }

    public List<Reserva> getReservaByUser(int id_usuario) {
        return reservaCrudRepository.findAllByIdUsuario(id_usuario);
    }

    public Optional<Reserva> getReserva(int idreserva){
        return reservaCrudRepository.findById(idreserva);
    }

    public void delete(Reserva reserva) {
        reservaCrudRepository.delete(reserva);
    }
}
