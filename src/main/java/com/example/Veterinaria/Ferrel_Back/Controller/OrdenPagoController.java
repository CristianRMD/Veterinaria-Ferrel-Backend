package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoService;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
@CrossOrigin
public class OrdenPagoController {
    // eliminar esta clase
    @Autowired
    private OrdenPagoService ordenPagoService;

    @PostMapping("/crear")
    public OrdenDePago crearOrden(@RequestBody List<ProductoOrden> productosOrden) {
        return ordenPagoService.crearOrdenPago(productosOrden);
    }

    @GetMapping
    public List<OrdenDePago> obtenerOrdenes() {
        return ordenPagoService.obtenerOrdenes();
    }
}
