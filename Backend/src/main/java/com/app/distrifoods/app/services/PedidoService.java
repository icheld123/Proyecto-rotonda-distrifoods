/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Pedido;
import com.app.distrifoods.app.entities.Sucursal;
import com.app.distrifoods.app.repository.PedidoRepository;
import com.app.distrifoods.app.repository.SucursalRepository;
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
    
    
    public List<Pedido> getAll() {
        return repository.getAll();
    }
    
    public Optional<Pedido> getPedido(int id) {
        return repository.getPedido(id);
    }
    
    public Pedido save(Pedido pedido) {
        
        boolean existeCliente = clienteService.existId(pedido.getId_cliente());
        boolean existeMetodoPago = metodoPagoService.existId(pedido.getId_metodo());
        boolean existeEstado = estadoService.existId(pedido.getId_estado());
        boolean entradasCorrectas = !pedido.getFecha().toString().isEmpty() && pedido.getPrecio() > 0;
        
        if (pedido.getId() == null && existeCliente && existeMetodoPago && existeEstado && entradasCorrectas) {
            return repository.save(pedido);
        } 
        else if (pedido.getId() != null && existeCliente && existeMetodoPago && existeEstado && entradasCorrectas) {
            if (repository.existId(pedido.getId())){
                return pedido;
            }
            else {
                return repository.save(pedido);
            }
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
                if (pedido.getId_cliente() != null) {
                    if (clienteService.existId(pedido.getId_cliente())){
                        resultado.get().setId_cliente(pedido.getId_cliente());
                    }
                }
                if (pedido.getId_metodo() != null) {
                    if (metodoPagoService.existId(pedido.getId_metodo())){
                        resultado.get().setId_metodo(pedido.getId_metodo());
                    }
                }
                if (pedido.getId_estado() != null) {
                    if (estadoService.existId(pedido.getId_estado())){
                        resultado.get().setId_estado(pedido.getId_estado());
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
