/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.controllers;

import com.app.distrifoods.app.entities.Pedido;
import com.app.distrifoods.app.entities.Producto;
import com.app.distrifoods.app.dto.PedidoDto;
import com.app.distrifoods.app.dto.PedidoDto_Consulta;
import com.app.distrifoods.app.dto.ProductoCompletoDto;
import com.app.distrifoods.app.services.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("pedido")
@CrossOrigin(origins = "*")
public class PedidoController {
    @Autowired
    private PedidoService service;
    
//    @GetMapping("/all")
//    public List<Pedido> getAll(){
//        return service.getAll();
//    }
    
    @GetMapping("/all")
    public List<PedidoDto_Consulta> getAll(){
        return service.getAll();
    }
    
    @PostMapping("/validar")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public List<Producto> save(@RequestBody List<ProductoCompletoDto> productoCompletoDto){
         return service.validarDisponibilidadProductos(productoCompletoDto);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Pedido save(@RequestBody PedidoDto pedidoDto){
         return service.save(pedidoDto);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED) //Anotacion que retorna el status
    public Pedido update(@RequestBody Pedido pedido){
        return service.update(pedido);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Anotacion que retorna el status
    public boolean delete(@PathVariable("id")int id){
        return service.deletePedido(id);
    }
}
