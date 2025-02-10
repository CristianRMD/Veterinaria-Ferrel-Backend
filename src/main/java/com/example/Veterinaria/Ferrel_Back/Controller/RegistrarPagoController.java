package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoRepository;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.RegistrarPagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class RegistrarPagoController {

    @Autowired
    private RegistrarPagosService RegistrarPagosService;

    @Autowired
    private OrdenPagoRepository OrdenPagoRepository;


    // validacion de documento (verificacion si es cliente)
    @GetMapping("/validar-documento/{documento}")
    public ResponseEntity<Boolean> validarDocumento(@PathVariable String documento) {
        return ResponseEntity.ok(RegistrarPagosService.validarDocumento(documento));
    }


    // verificar si el id_orden_compra existe manejar el flujo para agregarlo dentro de un carrito temporal y quitarlo de este carrito temporal

    // agregar costo de la consulta dentro del carrito temporal y manejar el flujo de agregar y quitar del carrito temporal

    // el formato de esta parte en adelante es
    // TIPO           DESCRIPCION        CANTIDAD   PRECIO TOTAL
    // por ejemplo si es de una orden de compra sera:
    // Producto       [nombre del producto]    [cantidad del producto]     [precio unitario del producto * cantidad del producto]
    // y si es consto de consulta debera de ser asi:
    // Servicio      Consulta                1                  [precio de la consulta que se agregue dentro del casillero]



    // listar todas las ordenes temporales con el formato:
    // TIPO           DESCRIPCION        CANTIDAD   PRECIO TOTAL
    @GetMapping("/ordenes")
    public List<OrdenDePago> obtenerOrdenes() {
        return OrdenPagoRepository.findAll();
    }

    // recordar que se debe estar sumando constantemente el precio total de cada fila y mostrarla con el +18% de igv

    // guardar el recibo
    @PostMapping("/registrar")
    public boolean registrarRecibo(@RequestParam int id_cliente, @RequestParam int id_orden, @RequestParam double monto_total) {
        return RegistrarPagosService.registrarRecibo(id_cliente, id_orden, monto_total);
    }


}
