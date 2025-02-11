package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoService;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.CarritoReciboDTO;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.CarritoReciboService;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.Recibo;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.ReciboService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoReciboController {

    private final CarritoReciboService carritoReciboService;
    private final OrdenPagoService ordenDePagoService;
    private final ReciboService reciboService;

    public CarritoReciboController(CarritoReciboService carritoReciboService, OrdenPagoService ordenDePagoService, ReciboService reciboService) {
        this.carritoReciboService = carritoReciboService;
        this.ordenDePagoService = ordenDePagoService;
        this.reciboService = reciboService;
    }

    @GetMapping("/listar")
    public List<CarritoReciboDTO> listarCarrito() {
        return carritoReciboService.listarCarrito();
    }

    @PostMapping("/agregar/{idOrden}")
    public void agregarOrden(@PathVariable Long idOrden) {
        OrdenDePago orden = ordenDePagoService.obtenerPorId(idOrden);

        if (orden != null) {
            carritoReciboService.agregarOrden(orden);
        }
    }

    @DeleteMapping("/quitar/{idOrden}")
    public void quitarOrden(@PathVariable Long idOrden) {
        carritoReciboService.quitarOrden(idOrden);
    }


    // verifica que el carrito no este vacio y vincula cada orden con el recibo antes de limpiarlo
    @PostMapping("/confirmar/{idCliente}")
    public Recibo generarRecibo(@PathVariable Long idCliente) {
        List<OrdenDePago> ordenes = carritoReciboService.obtenerCarrito();
        List<CarritoReciboDTO> consultas = carritoReciboService.obtenerConsultas();

        if (ordenes.isEmpty() && consultas.isEmpty()) {
            throw new RuntimeException("Carrito vacío");
        }

        Recibo nuevoRecibo = reciboService.crearRecibo(ordenes, idCliente);

        for (OrdenDePago orden : ordenes) {
            reciboService.vincularOrdenARecibo(nuevoRecibo.getIdRecibo(), orden.getIdOrden());
        }

        carritoReciboService.limpiarCarrito(); // Limpia órdenes y consultas
        return nuevoRecibo;
    }

    @PostMapping("/agregar-consulta")
    public void agregarConsulta(@RequestParam double precioTotal) {
        carritoReciboService.agregarConsulta(precioTotal);
    }

    @DeleteMapping("/quitar-consulta")
    public void quitarConsulta() {
        carritoReciboService.quitarConsulta();
    }


}
