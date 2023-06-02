/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.services;

import com.app.distrifoods.app.entities.Adicion;
import com.app.distrifoods.app.entities.Cliente;
import com.app.distrifoods.app.entities.DetallePedido;
import com.app.distrifoods.app.entities.DetalleProducto;
import com.app.distrifoods.app.entities.Estado;
import com.app.distrifoods.app.entities.MetodoPago;
import com.app.distrifoods.app.entities.Pedido;
import com.app.distrifoods.app.entities.Producto;
import com.app.distrifoods.app.entities.Usuario;
import com.app.distrifoods.app.dto.ClienteDto;
import com.app.distrifoods.app.dto.PedidoDto;
import com.app.distrifoods.app.dto.PedidoDto_Consulta;
import com.app.distrifoods.app.dto.ProductoCompletoDto;
import com.app.distrifoods.app.dto.ProductoDto;
import com.app.distrifoods.app.dto.Producto_AdicionesDto;
import com.app.distrifoods.app.repository.PedidoRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PedidoService {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private AdicionService adicionService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MetodoPagoService metodoPagoService;
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private DetallePedidoService detallePedidoService;
    @Autowired
    private DetalleProductoService detalleProductoService;
    @Autowired
    private ProductoService productoService;
    
    final int ID_ESTADO_INICIAL = 1;
//    public List<Pedido> getAll() {
//        return repository.getAll();
//    }
    
    public Optional<Pedido> getPedido(int id) {
        return repository.getPedido(id);
    }
    
    public List<PedidoDto_Consulta> getAll() {
        
        List<PedidoDto_Consulta> pedidosDto_Consulta = new ArrayList<>();
        List<Pedido> pedidos = repository.getAll();
        System.out.println("CANTIDAD DE PEDIDOS: " + pedidos.size());
        
        for(Pedido pedido :pedidos) {
            Optional<Cliente> cliente = clienteService.getCliente(pedido.getIdCliente());
            Optional<Usuario> usuario = usuarioService.getUsuario(cliente.get().getIdUsuario());
            ClienteDto clienteDto = new ClienteDto( cliente.get().getId(), 
                                                    usuario.get(),
                                                    cliente.get().getDireccion(),
                                                    cliente.get().getTelefono());
            Optional<MetodoPago> metodoPago = metodoPagoService.getMetodoPago(pedido.getIdMetodo());
            Optional<Estado> estado = estadoService.getEstado(pedido.getIdEstado());
            List<DetallePedido> detallePedido = detallePedidoService.getAllByIdPedido(pedido.getId());
            System.out.println("****************");
            System.out.println(detallePedido.size());
            System.out.println("****************");
            List<ProductoCompletoDto> productosCompletoDto = new ArrayList<>();
            for(DetallePedido detPedido :detallePedido) {
                ProductoDto productoDto = productoService.getProductoMapeado(detPedido.getIdProducto());
                List<DetalleProducto> detalleProducto = detalleProductoService.getAllByIdPedido(detPedido.getId());
                List<Adicion> adiciones = new ArrayList<>();
                if(detalleProducto != null && !detalleProducto.isEmpty()){
                    for(DetalleProducto detProducto :detalleProducto) {
                        Optional<Adicion> adicion = adicionService.getAdicion(detProducto.getIdAdicion());
                        adiciones.add(adicion.get());
                    }
                }
                ProductoCompletoDto productoCompletoDto = new ProductoCompletoDto(productoDto.getId(),
                                                                                    productoDto.getRestaurante(),
                                                                                    productoDto.getNombre(),
                                                                                    productoDto.getTipoProducto(),
                                                                                    productoDto.getCantidad(),
                                                                                    productoDto.getDescripcion(),
                                                                                    adiciones,
                                                                                    productoDto.getImagen(),
                                                                                    productoDto.getPrecio());
                if(productoCompletoDto.getId() > 0){
                     productosCompletoDto.add(productoCompletoDto);
                }
            }
            
            PedidoDto_Consulta pedidoDto_Consulta = new PedidoDto_Consulta();
            pedidoDto_Consulta.setId(pedido.getId());
            pedidoDto_Consulta.setCliente(clienteDto);
            pedidoDto_Consulta.setMetodoPago(metodoPago.get());
            pedidoDto_Consulta.setFecha(pedido.getFecha());
            pedidoDto_Consulta.setMetodoPago(metodoPago.get());
            pedidoDto_Consulta.setEstado(estado.get());
            pedidoDto_Consulta.setPrecio(pedido.getPrecio());
            pedidoDto_Consulta.setProductos(productosCompletoDto);
            
            pedidosDto_Consulta.add(pedidoDto_Consulta);
        }
        
        
        return pedidosDto_Consulta;
    }
    
    
    public Pedido save(PedidoDto pedidoDto) {
        Pedido pedido = new Pedido();
        boolean existeCliente = clienteService.existId(pedidoDto.getIdCliente());
        boolean existeMetodoPago = metodoPagoService.existId(pedidoDto.getIdMetodo());
        boolean existeEstado = estadoService.existId(pedidoDto.getIdEstado());
        boolean entradasCorrectas = !pedidoDto.getFecha().toString().isEmpty() && pedidoDto.getPrecio() > 0;

        if (existeCliente && existeMetodoPago && existeEstado && entradasCorrectas) {
            pedido = new Pedido(null, 
                                pedidoDto.getIdCliente(), 
                                pedidoDto.getIdMetodo(), 
                                pedidoDto.getFecha(), 
                                ID_ESTADO_INICIAL,
                                pedidoDto.getPrecio());
            
            if (validarDisponibilidadProductos(pedidoDto.getProductos()).isEmpty()) {
                Pedido pedidoAlmacenado = repository.save(pedido);
                System.out.println("*****************************");
                System.out.println("id PedidoAlmacenado: " + pedidoAlmacenado.getId());
                System.out.println("*****************************");
                if(pedidoAlmacenado.getId() > 0){
                    if(detallePedidoService.save(pedidoDto.getProductos(), pedidoAlmacenado.getId())){

                        System.out.println("*****************************");
                        System.out.println("PROBLEMA ALMACENANDO DETALLES PEDIDO");
                        System.out.println("*****************************");

                    }
                }
            
                return pedidoAlmacenado;
            }
            else{
                return pedido;
            }
            
        } 
        else {
            return pedido;
        }
    }
    
    
    public List<Producto> validarDisponibilidadProductos(List<ProductoCompletoDto> productos){
        Map<Integer, Integer> frecuencias = new HashMap<>();  // idProducto, frecuencia
        List<Producto> productosRepetidos = new ArrayList<>();
        
        for(ProductoCompletoDto producto :productos) {
            if (frecuencias.containsKey(producto.getId())) {
                frecuencias.put(producto.getId(), frecuencias.get(producto.getId()) + 1);
            } else {
                frecuencias.put(producto.getId(), 1);
            }
        }
        
        for (Map.Entry<Integer, Integer> entry : frecuencias.entrySet()) {
            int idProducto = entry.getKey();
            int frecuencia = entry.getValue();
            Optional<Producto> producto = productoService.getProducto(idProducto);
            if (frecuencia > producto.get().getCantidad()){
                productosRepetidos.add(producto.get());
            }
        }
        return productosRepetidos;
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
