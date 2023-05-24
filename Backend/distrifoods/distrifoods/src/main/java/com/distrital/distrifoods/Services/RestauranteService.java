package com.distrital.cinedistrito.Services;
import com.distrital.cinedistrito.Entity.Restaurante;
import com.distrital.cinedistrito.Repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private SucursalService sucursalService;
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private RestauranteService sedeService;
    final int CANTIDAD_RESTAURANTES = 10;
    String restaurantes[] = {"Muy", "McDonald's","La Brasa Roja","PizzaHut","PizzaLove","Kaminasi Sushi Wok","Crepes & Waffles"};
    String sucursales[] = {"Muy", "McDonald's","La Brasa Roja","PizzaHut","PizzaLove","Kaminasi Sushi Wok","Crepes & Waffles"};


    public void crearRestaurante(){
        for(int i = 0; i<CANTIDAD_RESTAURANTES; i++){
            Restaurante rest = new Restaurante(null,restaurantes[i]);
            save(rest);
            sucursalService.crearSucursal(sucursales[i], i+1);
        }
    }
    public void save(Restaurante restaurante) {
        restauranteRepository.save(restaurante);
    }

    public List<Restaurante> getAll() {
        return restauranteRepository.getAll();
    }

    public Optional<Restaurante> getRestaurante(int id) {
        return restauranteRepository.getRestaurante(id);
    }

//    public List<Restaurante> getSalaBySede(int sede) {
//        return restauranteRepository.getSalaBySede(sede);
//    }
}