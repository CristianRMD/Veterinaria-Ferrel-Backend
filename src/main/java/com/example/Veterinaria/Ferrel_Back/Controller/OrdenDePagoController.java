package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoService;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto.Producto;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto.ProductoService;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin
public class OrdenDePagoController {
    @Autowired
    private OrdenPagoService ordenPagoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoOrdenRepository productoOrdenRepository;

    private List<ProductoOrden> carrito = new ArrayList<>();

    // endpoint para seleccionar los productos /seleccionar?idProducto=1&cantidad=2
    @PostMapping("/seleccionar")
    public String seleccionarProducto(@RequestParam Integer idProducto, @RequestParam int cantidad) {
        Producto producto = productoService.obtenerPorId(idProducto);
        if (producto == null) {
            return "Producto no encontrado :c";
        }

        ProductoOrden productoOrden = new ProductoOrden(producto, cantidad);
        carrito.add(productoOrden);

        return "Producto agregado: " + producto.getNombre() + ", Cantidad: " + cantidad;
    }

    // trae todos los productos seleccionados

    /*
    * Ojo: saldra en este tipo de formato:
    * [
    {
        "id": null,
        "productoNombre": "Alimento para Perros",
        "cantidad": 2,
        "precioUnitario": 50.0,
        "subtotal": 100.0,
        "id_producto": 1
    }
]   aparecera el id null ya que como aun no se confirma la compra, se encuentra en el carrito temporal
* (me avisan si tienen problemas ya que se le puede agregar un id_temporal pero no lo veo necesario)
    * */
    @GetMapping("/productos-seleccionados")
    public List<ProductoOrden> listarProductosSeleccionados() {
        return carrito;
    }

    // endpoint para confirmar orden de pago -> devuelve los productos comprados
    @PostMapping("/confirmar")
    public OrdenDePago confirmarOrden() {
        if (carrito.isEmpty()) {
            return null;
        }

        OrdenDePago orden = ordenPagoService.crearOrdenPago(carrito);
        carrito.clear();

        return orden;
    }


    // lista todas las ordenes existentes
    @GetMapping
    public List<OrdenDePago> listarOrdenes() {
        return ordenPagoService.obtenerOrdenes();
    }

}