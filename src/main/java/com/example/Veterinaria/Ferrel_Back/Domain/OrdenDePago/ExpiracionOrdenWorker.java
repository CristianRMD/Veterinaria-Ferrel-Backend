package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;

import com.example.Veterinaria.Ferrel_Back.Domain.Producto.Producto;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto.ProductoService;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ExpiracionOrdenWorker {
    @Autowired
    private OrdenPagoRepository ordenRepo;
    @Autowired
    private ProductoService productoService;

    @Scheduled(fixedRate = 60000)
    @Transactional
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
