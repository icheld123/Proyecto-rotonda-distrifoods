/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.entities.Producto;
import com.app.distrifoods.app.entities.Restaurante;
import com.app.distrifoods.app.entities.TipoProducto;
import com.app.distrifoods.app.entities.dto.ProductoCompletoDto;
import com.app.distrifoods.app.entities.dto.ProductoDto;
import com.app.distrifoods.app.repository.ProductoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public List<ProductoCompletoDto> getAllMapeado() {
        List<Producto> respuesta = repository.getAll();
        return mapearProductoCompleto(respuesta);
    }
    
    public List<ProductoCompletoDto> prueba() {
        List<Producto> respuesta = repository.getAll();
        return mapearProductoCompleto(respuesta);
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
    
    public List<ProductoCompletoDto> getAllByRestaurant(int id){
        List<Producto> respuesta = repository.getByRestaurant(id);
        return mapearProductoCompleto(respuesta);
    }
    
    public List<ProductoCompletoDto> mapearProductoCompleto(List<Producto> listaProductos){
        List<ProductoCompletoDto> respuestaMapeada = new ArrayList<>();
        for (Producto producto : listaProductos) {
            Optional<Restaurante> restaurante = restauranteService.getRestaurante(producto.getIdRestaurante());
            Optional<TipoProducto> tipoProducto = tipoProductoService.getTipoProducto(producto.getIdTipoProducto());
//            List<Adicion> adiciones = new ArrayList<>();
            String urlString = "http://localhost:8080/api/adicion/all/byproducto/" + producto.getId();            
            String respuesta = consumirApiRest(urlString);
            System.out.println("/////////////////////////////");
            System.out.println(respuesta);
            System.out.println("/////////////////////////////");
            ObjectMapper mapper = new ObjectMapper();
            List<Adicion> adiciones = new ArrayList<>();
            try {
                adiciones = mapper.readValue(respuesta, new TypeReference<List<Adicion>>(){});
            } catch (JsonProcessingException ex) {
                Logger.getLogger(ProductoService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ProductoCompletoDto productoCompletoDto = new ProductoCompletoDto(producto.getId(), 
                                                      restaurante.get(),
                                                      producto.getNombre(),
                                                      tipoProducto.get(),
                                                      producto.getCantidad(),
                                                      producto.getDescripcion(),
                                                      adiciones,
                                                      producto.getImagen(),
                                                      producto.getPrecio());
            respuestaMapeada.add(productoCompletoDto);
           
        }
        return respuestaMapeada;
    }
    
    public Optional<Producto> getProducto(int id) {
        return repository.getProducto(id);
    }
    
     public boolean existId(int id){
        return repository.existId(id);
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
    
    public String consumirApiRest(String urlString){
        try {
                // Crea la URL de la API REST que deseas consumir
                URL url = new URL(urlString);

                // Abre una conexion HTTP
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Configura el metodo HTTP y otros encabezados
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");

                // Obtiene la respuesta de la API
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Lee la respuesta de la API
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    
                    // Cierra la conexion
                    connection.disconnect();
                    
                    return response.toString();
                     
                } else {
                    System.out.println("Error al consumir la API. Codigo de respuesta: " + responseCode);
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }    
}
