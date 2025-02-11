package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrdenRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoReciboService {

    private final ProductoOrdenRepository productoOrdenRepository;
    private final List<OrdenDePago> carrito = new ArrayList<>();
    private final List<CarritoReciboDTO> consultasCarrito = new ArrayList<>();

    public CarritoReciboService(ProductoOrdenRepository productoOrdenRepository) {
        this.productoOrdenRepository = productoOrdenRepository;
    }

    public List<CarritoReciboDTO> listarCarrito() {
        List<CarritoReciboDTO> lista = new ArrayList<>();

        // devuelve todos los productos en las ordenes del carrito
        for (OrdenDePago orden : carrito) {
            List<ProductoOrden> productosOrdenados = productoOrdenRepository.findByOrdenDePago_IdOrden(orden.getIdOrden());
            for (ProductoOrden productoOrden : productosOrdenados) {
                lista.add(new CarritoReciboDTO(
                        productoOrden.getTipo(),
                        productoOrden.getProductoNombre(),
                        productoOrden.getCantidad(),
                        productoOrden.getSubtotal()
                ));
            }
        }


        lista.addAll(consultasCarrito);

        return lista;
    }

    public void agregarOrden(OrdenDePago orden) {
        if (!carrito.contains(orden)) {
            carrito.add(orden);
        }
    }

    public void quitarOrden(Long idOrden) {
        carrito.removeIf(o -> o.getIdOrden().equals(idOrden));
    }

    public void agregarConsulta(double precioTotal) {
        consultasCarrito.add(new CarritoReciboDTO("Servicio", "Consulta", 1, precioTotal));
    }

    public void quitarConsulta() {
        consultasCarrito.clear(); // quita una consulta a la vez
    }

    public void limpiarCarrito() {
        carrito.clear();
        consultasCarrito.clear();
    }

    public List<OrdenDePago> obtenerCarrito() {
        return carrito;
    }

    public List<CarritoReciboDTO> obtenerConsultas() {
        return consultasCarrito;
    }

}
