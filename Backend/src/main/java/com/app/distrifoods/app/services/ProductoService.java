/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Producto;
import com.app.distrifoods.app.entities.Restaurante;
import com.app.distrifoods.app.entities.TipoProducto;
import com.app.distrifoods.app.entities.dto.ProductoDto;
import com.app.distrifoods.app.repository.ProductoRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Autowired
    private TipoProductoService tipoProductoService;
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private ProductoRepository repository;
//    @Autowired
//    private AdicionService adicionService;
    
    
    public List<Producto> getAll() {
        return repository.getAll();
    }
    
    public List<ProductoDto> getAllMapeado() {
        List<Producto> respuesta = repository.getAll();
        return mapearProducto(respuesta);
    }
    
    public List<ProductoDto> mapearProducto(List<Producto> listaProductos){
        List<ProductoDto> respuestaMapeada = new ArrayList<>();
        for (Producto producto : listaProductos) {
            Optional<Restaurante> restaurante = restauranteService.getRestaurante(producto.getIdRestaurante());
            Optional<TipoProducto> tipoProducto = tipoProductoService.getTipoProducto(producto.getIdTipoProducto());
            
            ProductoDto productoDto = new ProductoDto(producto.getId(), 
                                                      restaurante.get().getNombre(),
                                                      producto.getNombre(),
                                                      tipoProducto.get().getNombre(),
                                                      producto.getCantidad(),
                                                      producto.getDescripcion(),
                                                      producto.getImagen(),
                                                      producto.getPrecio());
            respuestaMapeada.add(productoDto);
        }
        return respuestaMapeada;
    }
    
    public Optional<Producto> getProducto(int id) {
        return repository.getProducto(id);
    }
    
     public boolean existId(int id){
        return repository.existId(id);
    }
    
    public List<ProductoDto> getAllByRestaurant(int id){
        List<Producto> respuesta = repository.getByRestaurant(id);
        return mapearProducto(respuesta);
    }
    
    public Producto save(Producto producto) {
        
        System.out.println("******************************************");
        System.out.println(producto);
        boolean existeTipoProducto = tipoProductoService.existId(producto.getIdTipoProducto());
        boolean existeRestaurante = restauranteService.existId(producto.getIdRestaurante());
        boolean entradasCorrectas = !producto.getNombre().isEmpty() && !producto.getDescripcion().isEmpty()
                && !producto.getImagen().isEmpty() && producto.getPrecio() > 0.0 && producto.getCantidad() > 0;
        
        if (producto.getId() == null && existeTipoProducto && existeRestaurante && entradasCorrectas) {
            return repository.save(producto);
        } 
        else if (producto.getId() != null && existeTipoProducto && existeRestaurante && entradasCorrectas) {
            if (repository.existId(producto.getId())){
                return producto;
            }
            else {
                return repository.save(producto);
            }
        } 
        else {
            return producto;
        }
    }
    
    public Producto update(Producto producto) {
        
        if (producto.getId() != null) {
            if (repository.existId(producto.getId())) {
                Optional<Producto> resultado = repository.getProducto(producto.getId());
                if (producto.getNombre() != null && !producto.getNombre().isEmpty()) {
                    resultado.get().setNombre(producto.getNombre());
                }
                if (producto.getIdTipoProducto() != null) {
                    if (tipoProductoService.existId(producto.getIdTipoProducto())){
                        resultado.get().setIdTipoProducto(producto.getIdTipoProducto());
                    }
                }
                if (producto.getIdRestaurante() != null) {
                    if (restauranteService.existId(producto.getIdRestaurante())){
                        resultado.get().setIdRestaurante(producto.getIdRestaurante());
                    }
                }
                if (producto.getCantidad() != null && producto.getCantidad() > 0) {
                    resultado.get().setCantidad(producto.getCantidad());
                }
                if (producto.getDescripcion() != null && !producto.getDescripcion().isEmpty()) {
                    resultado.get().setDescripcion(producto.getDescripcion());
                }
                if (producto.getImagen() != null && !producto.getImagen().isEmpty()) {
                    resultado.get().setImagen(producto.getImagen());
                }
                if (producto.getPrecio() != null && producto.getPrecio() > 0.0) {
                    resultado.get().setPrecio(producto.getPrecio());
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return producto;
            }
        } else {
            return producto;
        }
    }
    
    public boolean deleteProducto(int id) {
        System.out.println("*********************************");
        System.out.println(id);
        Boolean aBoolean = getProducto(id).map(producto -> {
            repository.delete(producto);
            System.out.println("*********************************");
            System.out.println(producto.getId());
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
