package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;

import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrdenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenPagoService {

    @Autowired
    private OrdenPagoRepository ordenPagoRepository;
    @Autowired
    private ProductoOrdenRepository productoOrdenRepository;


    @Transactional
    public OrdenDePago crearOrdenPago(List<ProductoOrden> productosOrden) {
        if (productosOrden == null || productosOrden.isEmpty()) {
            throw new IllegalStateException("No hay productos en la orden de pago.");
        }

        double total = productosOrden.stream().mapToDouble(ProductoOrden::getSubtotal).sum();

        OrdenDePago orden = new OrdenDePago();
        orden.setTotal(total);

        for (ProductoOrden po : productosOrden) {
            po.setOrdenDePago(orden);
        }

        orden.setProductos(new ArrayList<>(productosOrden));
        orden = ordenPagoRepository.save(orden);

        return orden;
    }


    public List<OrdenDePago> obtenerOrdenes() {
        return ordenPagoRepository.findAll();
    }


    // crear metodo para obtener por id

    public OrdenDePago obtenerPorId(Long idOrden) {
        return ordenPagoRepository.findById(idOrden).orElse(null);
    }

}
