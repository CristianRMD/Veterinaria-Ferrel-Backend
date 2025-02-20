
package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.EstadoOrden;
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

        for (ProductoOrden pOrden : carrito) {
            if (pOrden.getProducto().getId_producto() == idProducto) {
                return "Este producto ya está en el carrito";
            }
        }

        if (!producto.reducirStock(cantidad)) {
            return "No hay suficiente stock disponible para " + producto.getNombre();
        }

        productoService.guardarProducto(producto);
        ProductoOrden productoOrden = new ProductoOrden(producto, cantidad);
        productoOrden.setStockDescontado(true);
        carrito.add(productoOrden);

        ordenPagoService.programarLiberacionStock(productoOrden, 15);

        return "Producto reservado: " + producto.getNombre() + ", Cantidad: " + cantidad;
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

        List<ProductoOrden> productos = new ArrayList<>(orden.getProductos()); // Cargar antes de usar

        for (ProductoOrden productoOrden : productos) {
            //productoOrden.getProducto().confirmarCompra(productoOrden.getCantidad());
            productoService.guardarProducto(productoOrden.getProducto());
            ordenPagoService.cancelarLiberacionStock(productoOrden);
        }


        orden.setEstado(EstadoOrden.PENDIENTE);
        ordenPagoService.guardarOrden(orden);
        carrito.clear();
        return orden;
    }

    // endpoint para cancelar la orden de pago manualmente -> en carrito estara para que se haga de forma automatica (15 min)
    // se debe de cancelar antes de confirmar sino muere
    @PostMapping("/cancelar")
    public String cancelarOrden() {
        for (ProductoOrden productoOrden : carrito) {
            if (productoOrden.isStockDescontado()) {
                productoOrden.getProducto().restaurarStock(productoOrden.getCantidad());
                productoOrden.setStockDescontado(false);
                productoService.guardarProducto(productoOrden.getProducto());
            }
            ordenPagoService.cancelarLiberacionStock(productoOrden);
        }
        carrito.clear();
        return "Orden cancelada, stock restaurado";
    }


    // lista todas las ordenes existentes
    @GetMapping
    public List<OrdenDePago> listarOrdenes() {
        return ordenPagoService.obtenerOrdenes();
    }

}