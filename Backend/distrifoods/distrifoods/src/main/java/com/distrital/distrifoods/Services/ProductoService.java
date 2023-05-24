package com.distrital.cinedistrito.Services;
import com.distrital.cinedistrito.Entity.Producto;
import com.distrital.cinedistrito.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAll() {
        return productoRepository.getAll();
    }

    public int getProductoById(int producto) {
        Optional<Producto> resultado = productoRepository.findAllById(producto);
        if (resultado.isPresent()) {
            return resultado.get().getId_producto();
        }
        return 0;
    }
    public Optional<Producto> getProducto(int producto) {
        return productoRepository.getProducto(producto);
    }

    public Producto save(Producto producto) {
        Optional<Producto> resultado = productoRepository.findAllById(producto.getId_producto());
        if (producto.getId_producto() == null) {
            if (resultado.isPresent()) {
                return producto;
            }
            return productoRepository.save(producto);
        } else {
            if (resultado.isPresent()) {
                return producto;
            } else {
                return productoRepository.save(producto);
            }
        }
    }

    public boolean deleteEmpleado(int producto) {
        Boolean aBoolean = getProducto(producto).map(client -> {
            productoRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
