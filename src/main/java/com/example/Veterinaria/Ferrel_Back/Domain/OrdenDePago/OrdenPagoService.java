package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;

import com.example.Veterinaria.Ferrel_Back.Domain.Producto.Producto;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto.ProductoService;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrdenRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class OrdenPagoService {

    @Autowired
    private OrdenPagoRepository ordenPagoRepository;
    @Autowired
    private ProductoOrdenRepository productoOrdenRepository;

    private final ProductoService productoService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Map<ProductoOrden, ScheduledFuture<?>> tareasLiberacion = new ConcurrentHashMap<>();

    public OrdenPagoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void programarLiberacionStock(ProductoOrden productoOrden, int minutos) {
        ScheduledFuture<?> tarea = scheduler.schedule(() -> {
            synchronized (productoOrden.getProducto()) {
                if (!productoOrden.isStockDescontado()) {
                    return;
                }
                productoOrden.getProducto().restaurarStock(productoOrden.getCantidad());
                productoOrden.setStockDescontado(false);
                productoService.guardarProducto(productoOrden.getProducto());
            }
        }, minutos, TimeUnit.MINUTES);

        tareasLiberacion.put(productoOrden, tarea);
    }


    public void cancelarLiberacionStock(ProductoOrden productoOrden) {
        ScheduledFuture<?> tarea = tareasLiberacion.remove(productoOrden);
        if (tarea != null) {
            tarea.cancel(false);
        }
    }


    @Transactional
    public OrdenDePago crearOrdenPago(List<ProductoOrden> productosOrden) {
        if (productosOrden == null || productosOrden.isEmpty()) {
            throw new IllegalStateException("No hay productos en la orden de pago :C");
        }

        double total = productosOrden.stream().mapToDouble(ProductoOrden::getSubtotal).sum();
        OrdenDePago orden = new OrdenDePago();
        orden.setTotal(total);

        for (ProductoOrden po : productosOrden) {
            Producto producto = po.getProducto();

            // Verificar si el stock ya fue descontado previamente
            if (!po.isStockDescontado()) {
                if (!producto.reducirStock(po.getCantidad())) {
                    throw new IllegalStateException("No hay suficiente stock para el producto: " + producto.getNombre());
                }
                po.setStockDescontado(true);
            }

            po.setOrdenDePago(orden);
        }

        orden.setProductos(new ArrayList<>(productosOrden));
        return ordenPagoRepository.save(orden);
    }


    // un poco renundante pero por facilismo lo coloco (OBS - 3er semanaFEB//2025)
    public void guardarOrden(OrdenDePago orden) {
        ordenPagoRepository.save(orden);
    }

    public OrdenDePago obtenerOrdenConProductos(Long id) {
        OrdenDePago orden = ordenPagoRepository.findById(id).orElse(null);
        if (orden != null) {
            Hibernate.initialize(orden.getProductos());
        }
        return orden;
    }

    public List<OrdenDePago> obtenerOrdenes() {
        return ordenPagoRepository.findAll();
    }


    // crear metodo para obtener por id

    public OrdenDePago obtenerPorId(Long idOrden) {
        return ordenPagoRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }
}
