/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.entities.Cliente;
import com.app.distrifoods.app.entities.DetallePedido;
import com.app.distrifoods.app.entities.Pedido;
import com.app.distrifoods.app.entities.dto.ProductoAdicionesDto;
import com.app.distrifoods.app.entities.dto.ProductoCompletoDto;
import com.app.distrifoods.app.repository.DetallePedidoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoService {
    @Autowired
    private DetallePedidoRepository repository;
     @Autowired
    private DetalleProductoService detalleProductoService;
    
    public final int TAMANO_TELEFONO = 10; 
    
    public List<DetallePedido> getAll() {
        return repository.getAll();
    }
    
    public List<DetallePedido> getAllByIdPedido(int id) {
        return repository.getAllByIdPedido(id);
    }
    
    public Optional<DetallePedido> getDetallePedido(int id) {
        return repository.getDetallePedido(id);
    }

    
    public boolean existId(int id){
        return repository.existId(id);
    }
    
    public boolean save(List<ProductoCompletoDto> productos, int IdPedido) {
        DetallePedido detallePedido;
        DetallePedido detallePedidoGuardado;
        int contadorProductos = 0;
        boolean validador = true;
        
        if(!productos.isEmpty()){
            for(ProductoCompletoDto producto :productos) {
                System.out.println(producto.getId());
                detallePedido = new DetallePedido(null, IdPedido, producto.getId());
                detallePedidoGuardado = repository.save(detallePedido);
                if (detallePedidoGuardado.getId()> 0){
                    contadorProductos++;
                    if( producto.getAdiciones() != null && validador){
                        validador = detalleProductoService.save(producto.getAdiciones(), detallePedidoGuardado.getId());
                    }
                }
            }
        }
        
        if(!validador){
            System.out.println("*****************************");
            System.out.println("PROBLEMA ALMACENANDO DETALLE ADICIONES");
            System.out.println("*****************************");
        }
        
        System.out.println("*****************************");
        System.out.println("cantidad productos: " + productos.size());
        System.out.println("cantidad detalles almacenados: " + contadorProductos);
        System.out.println("*****************************");
        return (productos.size() == contadorProductos && validador);
    }
    
    
//    public Cliente update(Cliente cliente) {
//        if (cliente.getId() != null) {
//            if (repository.existId(cliente.getId())) {
//                Optional<Cliente> resultado = repository.getCliente(cliente.getId());
//                if (cliente.getDireccion() != null && !cliente.getDireccion().isEmpty()) {
//                    resultado.get().setDireccion(cliente.getDireccion());
//                }
//                if (cliente.getTelefono() != null && cliente.getTelefono() > 0 
//                        && cliente.getTelefono().toString().length() == TAMANO_TELEFONO) {
//                    resultado.get().setTelefono(cliente.getTelefono());
//                }
//                repository.save(resultado.get());
//                return resultado.get();
//            } else {
//                return cliente;
//            }
//        } else {
//            return cliente;
//        }
//    }
    
//    public boolean deleteCliente(int id) {
//        Boolean aBoolean = getCliente(id).map(cliente -> {
//            repository.delete(cliente);
//            return true;
//        }).orElse(false);
//        return aBoolean;
//    }
}
