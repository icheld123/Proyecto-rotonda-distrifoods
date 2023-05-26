/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Cliente;
import com.app.distrifoods.app.entities.DetallePedido;
import com.app.distrifoods.app.entities.Pedido;
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
    
    public final int TAMANO_TELEFONO = 10; 
    
    public List<DetallePedido> getAll() {
        return repository.getAll();
    }
    
    public Optional<DetallePedido> getDetallePedido(int id) {
        return repository.getDetallePedido(id);
    }

    
    public boolean existId(int id){
        return repository.existId(id);
    }
    
    public boolean save(List<Integer> idsProductos, int IdPedido) {
        List<DetallePedido> detallesPedido = new ArrayList<>();
        List<DetallePedido> detallePedidoGuardado = new ArrayList<>();
        int contador = 0;
        if(!idsProductos.isEmpty()){
            for(int id :idsProductos) {
                detallesPedido.add(new DetallePedido(null, IdPedido, id));
            }
            detallePedidoGuardado = repository.saveAll(detallesPedido);
        }
        System.out.println("*****************************");
        System.out.println("cantidad productos: " + idsProductos.size());
        System.out.println("cantidad detalles almacenados: " + detallePedidoGuardado.size());
        System.out.println("*****************************");
        return (idsProductos.size() == detallePedidoGuardado.size());
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
