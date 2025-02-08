package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoRepository;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.RegistrarPagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class RegistrarPagoController {

    @Autowired
    private RegistrarPagosService RegistrarPagosService;

    @Autowired
    private OrdenPagoRepository OrdenPagoRepository;

    @GetMapping("/ordenes")
    public List<OrdenDePago> obtenerOrdenes() {
        return OrdenPagoRepository.findAll();
    }

    @PostMapping("/registrar")
    public boolean registrarRecibo(@RequestParam int id_cliente, @RequestParam int id_orden, @RequestParam double monto_total) {
        return RegistrarPagosService.registrarRecibo(id_cliente, id_orden, monto_total);
    }


}
