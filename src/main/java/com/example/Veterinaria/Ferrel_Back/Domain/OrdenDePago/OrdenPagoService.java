package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;

import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenPagoService {

    @Autowired
    private OrdenPagoRepository ordenPagoRepository;

    public OrdenDePago crearOrdenPago(List<ProductoOrden> productosOrden) {
        double total = productosOrden.stream().mapToDouble(ProductoOrden::getPrecio_total).sum();
        OrdenDePago orden = new OrdenDePago(null, total, productosOrden);
        return ordenPagoRepository.save(orden);
    }

    public List<OrdenDePago> obtenerOrdenes() {
        return ordenPagoRepository.findAll();
    }
}
