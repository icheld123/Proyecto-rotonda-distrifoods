/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.entities.Producto;
import com.app.distrifoods.app.entities.Restaurante;
import com.app.distrifoods.app.entities.TipoProducto;
import com.app.distrifoods.app.entities.dto.AdicionDto;
import com.app.distrifoods.app.entities.dto.ProductoDto;
import com.app.distrifoods.app.repository.AdicionRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AdicionService {
    @Autowired
    private AdicionRepository repository;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private RestauranteService restauranteService;
    
    
    
    public List<Adicion> getAll() {
        return repository.getAll();
    }
    
    public List<AdicionDto> getAllMapeado() {
        List<Adicion> respuesta = repository.getAll();
        return mapearAdicion(respuesta);
    }
    
    public List<AdicionDto> mapearAdicion(List<Adicion> listaAdiciones){
        List<AdicionDto> respuestaMapeada = new ArrayList<>();
        for (Adicion adicion : listaAdiciones) {
            Optional<Producto> producto = productoService.getProducto(adicion.getIdProducto());
            int idRestaurante = producto.get().getIdRestaurante();
            Optional<Restaurante> restaurante = restauranteService.getRestaurante(idRestaurante);
            
            AdicionDto adicionDto = new AdicionDto(adicion.getId(), 
                                                    producto.get().getNombre(),
                                                    restaurante.get().getNombre(),
                                                    adicion.getNombre(),
                                                    adicion.getPrecio());
            respuestaMapeada.add(adicionDto);
        }
        return respuestaMapeada;
    }
    
    public Optional<Adicion> getAdicion(int id) {
        return repository.getAdicion(id);
    }
    
    public List<Adicion> getAllByProducto(int id){
        return repository.getByProducto(id);
    }
    
    public Adicion save(Adicion adicion) {
        
        System.out.println("******************************************");
        System.out.println(adicion);
        boolean existeProducto = productoService.existId(adicion.getIdProducto());
        boolean entradasCorrectas = !adicion.getNombre().isEmpty() && adicion.getPrecio() > 0.0;
        
        if (adicion.getId() == null && existeProducto && entradasCorrectas) {
            return repository.save(adicion);
        } 
        else if (adicion.getId() != null && existeProducto && entradasCorrectas) {
            if (repository.existId(adicion.getId())){
                return adicion;
            }
            else {
                return repository.save(adicion);
            }
        } 
        else {
            return adicion;
        }
    }
    
    public Adicion update(Adicion adicion) {
        
        if (adicion.getId() != null) {
            if (repository.existId(adicion.getId())) {
                Optional<Adicion> resultado = repository.getAdicion(adicion.getId());
                if (adicion.getNombre() != null && !adicion.getNombre().isEmpty()) {
                    resultado.get().setNombre(adicion.getNombre());
                }
                if (adicion.getIdProducto() != null) {
                    if (productoService.existId(adicion.getIdProducto())){
                        resultado.get().setIdProducto(adicion.getIdProducto());
                    }
                }
                if (adicion.getPrecio() != null && adicion.getPrecio() > 0.0) {
                    resultado.get().setPrecio(adicion.getPrecio());
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return adicion;
            }
        } else {
            return adicion;
        }
    }
    
    public boolean deleteAdicion(int id) {
        System.out.println("*********************************");
        System.out.println(id);
        Boolean aBoolean = getAdicion(id).map(adicion -> {
            repository.delete(adicion);
            System.out.println("*********************************");
            System.out.println(adicion.getId());
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
