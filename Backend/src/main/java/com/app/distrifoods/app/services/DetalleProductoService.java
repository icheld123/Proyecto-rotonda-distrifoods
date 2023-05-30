/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.entities.DetallePedido;
import com.app.distrifoods.app.entities.DetalleProducto;
import com.app.distrifoods.app.repository.DetallePedidoRepository;
import com.app.distrifoods.app.repository.DetalleProductoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleProductoService {
    @Autowired
    private DetalleProductoRepository repository;
    
    public final int TAMANO_TELEFONO = 10; 
    
    public List<DetalleProducto> getAll() {
        return repository.getAll();
    }
    
    public List<DetalleProducto> getAllByIdPedido(int id) {
        return repository.getAllByIdPedido(id);
    }
    
    public Optional<DetalleProducto> getDetalleProducto(int id) {
        return repository.getDetalleProducto(id);
    }
    
//    public boolean existByIdPedido(int id){
//        return repository.existByIdPedido(id);
//    }
    
    public boolean existId(int id){
        return repository.existId(id);
    }
    
    public boolean save(List<Adicion> adiciones, int IdDetallePedido) {
        DetalleProducto detalleProducto;
        DetalleProducto detalleProductoGuardado;
        int contadorAdiciones = 0;
        
        if(!adiciones.isEmpty()){
            for(Adicion adicion :adiciones) {
                System.out.println(adicion.getId());
                detalleProducto = new DetalleProducto(null, IdDetallePedido, adicion.getId());
                detalleProductoGuardado = repository.save(detalleProducto);
                if (detalleProductoGuardado.getId()> 0){
                    contadorAdiciones++;
                }
            }
        }
        
        System.out.println("*****************************");
        System.out.println("cantidad adiciones: " + adiciones.size());
        System.out.println("cantidad detalles almacenados: " + contadorAdiciones);
        System.out.println("*****************************");
        return (adiciones.size() == contadorAdiciones);
    }
}
