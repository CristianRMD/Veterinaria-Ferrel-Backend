package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;

import com.example.Veterinaria.Ferrel_Back.Domain.Producto.Producto;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto.ProductoService;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ExpiracionOrdenWorker {
    @Autowired
    private OrdenPagoRepository ordenRepo;
    private ProductoService productoService;

    @Scheduled(fixedRate = 60000)
    public void verificarOrdenesExpiradas() {
        List<OrdenDePago> ordenesExpiradas = ordenRepo.findByEstadoAndFechaExpiracionBefore(
                EstadoOrden.PENDIENTE, LocalDateTime.now());

        for (OrdenDePago orden : ordenesExpiradas) {
            orden.setEstado(EstadoOrden.CANCELADO);

            for (ProductoOrden productoOrden : orden.getProductos()) {
                Producto producto = productoOrden.getProducto();
                producto.restaurarStock(productoOrden.getCantidad());
                productoService.guardarProducto(producto);
            }


            ordenRepo.save(orden);
        }
    }
}
