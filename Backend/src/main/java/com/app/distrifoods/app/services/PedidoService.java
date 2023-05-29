/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.entities.Pedido;
import com.app.distrifoods.app.entities.dto.PedidoDto;
import com.app.distrifoods.app.entities.dto.ProductoAdicionesDto;
import com.app.distrifoods.app.repository.DetallePedidoRepository;
import com.app.distrifoods.app.repository.PedidoRepository;;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PedidoService {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private MetodoPagoService metodoPagoService;
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private DetallePedidoService detallePedidoService;
    
    
    public List<Pedido> getAll() {
        return repository.getAll();
    }
    
    public Optional<Pedido> getPedido(int id) {
        return repository.getPedido(id);
    }
    
    public Pedido save(PedidoDto pedidoDto) {
        Pedido pedido = new Pedido();
//        System.out.println("*****************************");
//        System.out.println(pedidoDto.getPrecio());
//        System.out.println(pedidoDto.getFecha().toString());
//        if(!pedidoDto.getIdsPedidos().isEmpty()){
//            for(ProductoAdicionesDto producto :pedidoDto.getIdsPedidos()) {
//                System.out.println(producto.getId());
//                if(producto.getAdiciones() != null){
//                    for(Adicion adicion :producto.getAdiciones()){
//                        System.out.println(adicion.getNombre());
//                    }    
//                }
//                
//            }
//        }
//        
//        System.out.println("*****************************");
        
        boolean existeCliente = clienteService.existId(pedidoDto.getIdCliente());
        boolean existeMetodoPago = metodoPagoService.existId(pedidoDto.getIdMetodo());
        boolean existeEstado = estadoService.existId(pedidoDto.getIdEstado());
        boolean entradasCorrectas = !pedidoDto.getFecha().toString().isEmpty() && pedidoDto.getPrecio() > 0;
//    
        if (existeCliente && existeMetodoPago && existeEstado && entradasCorrectas) {
            pedido = new Pedido(null, 
                                        pedidoDto.getIdCliente(), 
                                        pedidoDto.getIdMetodo(), 
                                        pedidoDto.getFecha(), 
                                        pedidoDto.getIdEstado(),
                                        pedidoDto.getPrecio());
            Pedido pedidoAlmacenado = repository.save(pedido);
            System.out.println("*****************************");
            System.out.println("id PedidoAlmacenado: " + pedidoAlmacenado.getId());
            System.out.println("*****************************");
            if(pedidoAlmacenado.getId() > 0){
                if(detallePedidoService.save(pedidoDto.getIdsPedidos(), pedidoAlmacenado.getId())){
                 
                    System.out.println("*****************************");
                    System.out.println("PROBLEMA ALMACENANDO DETALLES PEDIDO");
                    System.out.println("*****************************");
                    
                }
            }
            
            return pedidoAlmacenado;
        } 
        else {
            return pedido;
        }
    }
    
    public Pedido update(Pedido pedido) {
        
        if (pedido.getId() != null) {
            if (repository.existId(pedido.getId())) {
                Optional<Pedido> resultado = repository.getPedido(pedido.getId());
//                if (pedido.getDireccion() != null && !pedido.getDireccion().isEmpty()) {
//                    resultado.get().setDireccion(pedido.getDireccion());
//                }
                if (pedido.getIdCliente() != null) {
                    if (clienteService.existId(pedido.getIdCliente())){
                        resultado.get().setIdCliente(pedido.getIdCliente());
                    }
                }
                if (pedido.getIdMetodo() != null) {
                    if (metodoPagoService.existId(pedido.getIdMetodo())){
                        resultado.get().setIdMetodo(pedido.getIdMetodo());
                    }
                }
                if (pedido.getIdEstado() != null) {
                    if (estadoService.existId(pedido.getIdEstado())){
                        resultado.get().setIdEstado(pedido.getIdEstado());
                    }
                }
                repository.save(resultado.get());
                return resultado.get();
            } else {
                return pedido;
            }
        } else {
            return pedido;
        }
    }
    
    public boolean deletePedido(int id) {
        Boolean aBoolean = getPedido(id).map(pedido -> {
            repository.delete(pedido);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
