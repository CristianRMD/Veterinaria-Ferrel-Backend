package com.example.Veterinaria.Ferrel_Back.Domain.Producto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }


    public Producto obtenerPorId(Integer idProducto) {
        return productoRepository.findById(idProducto).get();
    }


}
