package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.TipoProducto;
import com.app.distrifoods.app.repository.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class TipoProductoService {
    @Autowired
    private TipoProductoRepository repository;
    
    public List<TipoProducto> getAll() {
        return repository.getAll();
    }
    
    public Optional<TipoProducto> getTipoProducto(int id) {
        return repository.getTipoProducto(id);
    }
    
    public boolean existId(int id){
        return repository.existId(id);
    }
    
    public TipoProducto save(TipoProducto tipoProducto) {
        boolean entradasCorrectas = !tipoProducto.getNombre().isEmpty();
        
        if (tipoProducto.getId() == null && entradasCorrectas) {
            return repository.save(tipoProducto);
        } 
        else if (tipoProducto.getId() != null && entradasCorrectas){
            if (repository.existId(tipoProducto.getId())) {
                return tipoProducto;
            } else {
                return repository.save(tipoProducto);
            }
        }
        else {
            return tipoProducto;
        }
    }
    
    public TipoProducto update(TipoProducto tipoProducto) {
        if (tipoProducto.getId() != null) {
            if (repository.existId(tipoProducto.getId())) {
                Optional<TipoProducto> resultado = repository.getTipoProducto(tipoProducto.getId());
                if (tipoProducto.getNombre() != null && !tipoProducto.getNombre().isEmpty()) {
                    resultado.get().setNombre(tipoProducto.getNombre());
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return tipoProducto;
            }
        } else {
            return tipoProducto;
        }
    }
    
    public boolean deleteTipoProducto(int id) {
        Boolean aBoolean = getTipoProducto(id).map(tipoProducto -> {
            repository.delete(tipoProducto);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
