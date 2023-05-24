
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Estado;
import com.app.distrifoods.app.entities.MetodoPago;
import com.app.distrifoods.app.repository.EstadoRepository;
import com.app.distrifoods.app.repository.MetodoPagoRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository repository;
    
    public List<Estado> getAll() {
        return repository.getAll();
    }
    
    public Optional<Estado> getEstado(int id) {
        return repository.getEstado(id);
    }
    
    public boolean existId(int id){
        return repository.existId(id);
    }
    
    public Estado save(Estado estado) {
        boolean entradasCorrectas = !estado.getNombre().isEmpty();
        
        if (estado.getId() == null && entradasCorrectas) {
            return repository.save(estado);
        } 
        else if (estado.getId() != null && entradasCorrectas){
            if (repository.existId(estado.getId())) {
                return estado;
            } else {
                return repository.save(estado);
            }
        }
        else {
            return estado;
        }
    }
    
    public Estado update(Estado estado) {
        if (estado.getId() != null) {
            if (repository.existId(estado.getId())) {
                Optional<Estado> resultado = repository.getEstado(estado.getId());
                if (estado.getNombre() != null && !estado.getNombre().isEmpty()) {
                    resultado.get().setNombre(estado.getNombre());
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return estado;
            }
        } else {
            return estado;
        }
    }
    
    public boolean deleteEstado(int id) {
        Boolean aBoolean = getEstado(id).map(metodoPago -> {
            repository.delete(metodoPago);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
