package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoService;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto.ProductoService;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.CarritoReciboDTO;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.CarritoReciboService;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.Recibo;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.ReciboService;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.Cliente;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoReciboController {

    private final CarritoReciboService carritoReciboService;
    private final OrdenPagoService ordenDePagoService;
    private final ReciboService reciboService;
    private final ClienteRepository clienteRepository;
    private final ProductoService productoService;

    public CarritoReciboController(CarritoReciboService carritoReciboService, OrdenPagoService ordenDePagoService, ReciboService reciboService, ClienteRepository clienteRepository, ProductoService productoService) {
        this.carritoReciboService = carritoReciboService;
        this.ordenDePagoService = ordenDePagoService;
        this.reciboService = reciboService;
        this.clienteRepository = clienteRepository;
        this.productoService = productoService;
    }

    @GetMapping("/listar")
    public List<CarritoReciboDTO> listarCarrito() {
        return carritoReciboService.listarCarrito();
    }

    @PostMapping("/agregar/{idOrden}")
    public ResponseEntity<String> agregarOrden(@PathVariable Long idOrden) {
        OrdenDePago orden = ordenDePagoService.obtenerPorId(idOrden);

        if (orden != null) {
            carritoReciboService.agregarOrden(orden);
            return ResponseEntity.ok("Orden de pago agregada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Orden de pago no encontrada :c");
        }
    }


    @DeleteMapping("/quitar/{idOrden}")
    public void quitarOrden(@PathVariable Long idOrden) {
        carritoReciboService.quitarOrden(idOrden);
    }


    // para el caso de clientes no registrado usamos:
    // http://localhost:8080/carrito/confirmar/0
    @Transactional
    @PostMapping("/confirmar/{idCliente}")
    public Recibo generarRecibo(@PathVariable(required = false) Long idCliente) {
        Cliente cliente = null;

        if (idCliente != null && idCliente > 0) {
            cliente = clienteRepository.findById(idCliente).orElse(null);

            if (cliente != null && !cliente.getActivo()) {
                throw new RuntimeException("Cliente NO encontrado :C");
            }
        }

        List<OrdenDePago> ordenes = carritoReciboService.obtenerCarrito();
        List<CarritoReciboDTO> consultas = carritoReciboService.obtenerConsultas();

        if (ordenes.isEmpty() && consultas.isEmpty()) {
            throw new RuntimeException("Carrito vacio");
        }

        Recibo nuevoRecibo = reciboService.crearRecibo(ordenes, cliente != null ? cliente.getId() : null);

        for (OrdenDePago orden : ordenes) {
            for (ProductoOrden productoOrden : orden.getProductos()) {
                if (productoOrden.isStockDescontado()) {
                    continue;
                }
                productoOrden.setStockDescontado(true);
                productoService.guardarProducto(productoOrden.getProducto());
                ordenDePagoService.cancelarLiberacionStock(productoOrden);
            }
        }

        carritoReciboService.limpiarCarrito();
        return nuevoRecibo;
    }



    @PostMapping("/agregar-consulta/{precioTotal}")
    public int agregarConsulta(@PathVariable double precioTotal) {
        return carritoReciboService.agregarConsulta(precioTotal);
    }

    @DeleteMapping("/quitar-consulta/{index}")
    public void quitarConsulta(@PathVariable int index) {
        carritoReciboService.quitarConsulta(index);
    }



}
