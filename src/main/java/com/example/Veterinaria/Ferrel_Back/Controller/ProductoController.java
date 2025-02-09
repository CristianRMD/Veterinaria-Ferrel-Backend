package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.Producto.Producto;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin
public class ProductoController {

    //Consultar stock del producto
    // generar oden de ppago


    @Autowired
    private ProductoService productoService;

    // lista todos los productos para mostrarlos cuando inicia la interfaz
    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return productoService.listarTodos();
    }

    // buscar producto por nombre con coincidencias de palabras
    @GetMapping("/buscar")
    public List<Producto> buscarProducto(@RequestParam String nombre) {
        return productoService.buscarPorNombre(nombre);
    }
}
