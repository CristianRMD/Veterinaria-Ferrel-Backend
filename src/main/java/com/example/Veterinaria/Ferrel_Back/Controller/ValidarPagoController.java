package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.EstadoOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoRepository;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.ValidarPagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class ValidarPagoController {

    @Autowired
    private ValidarPagosService RegistrarPagosService;

    @Autowired
    private OrdenPagoRepository OrdenPagoRepository;


    // validacion de documento (verificacion si es cliente)
    @GetMapping("/validar-documento/{documento}")
    public ResponseEntity<Boolean> validarDocumento(@PathVariable String documento) {
        return ResponseEntity.ok(RegistrarPagosService.validarDocumento(documento));
    }


    @PostMapping("/confirmar-pago/{idOrden}")
    public ResponseEntity<String> confirmarPago(@PathVariable Long idOrden) {
        OrdenDePago orden = OrdenPagoRepository.findById(idOrden).orElse(null);

        if (orden == null) {
            return ResponseEntity.badRequest().body("Orden no encontrada");
        }

        if (!orden.getEstado().equals(EstadoOrden.PENDIENTE)) {
            return ResponseEntity.badRequest().body("La orden ya ha sido procesada...");
        }

        orden.setEstado(EstadoOrden.PAGADO);
        OrdenPagoRepository.save(orden);

        return ResponseEntity.ok("Pago confirmado correctamente");
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


}
