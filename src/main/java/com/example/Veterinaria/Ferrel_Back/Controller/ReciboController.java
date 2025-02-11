package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.CarritoReciboService;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.Recibo;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.ReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recibo")
public class ReciboController {
    @Autowired
    private ReciboService reciboService;

    @Autowired
    private CarritoReciboService carritoReciboService;

    // crea el recibo con las ordenes en el carrito y luego limpia el carrichi
    @PostMapping("/crear/{idCliente}")
    public Recibo crearRecibo(@PathVariable Long idCliente) {
        List<OrdenDePago> ordenes = carritoReciboService.obtenerCarrito();
        Recibo recibo = reciboService.crearRecibo(ordenes, idCliente);
        carritoReciboService.limpiarCarrito();
        return recibo;
    }
}
