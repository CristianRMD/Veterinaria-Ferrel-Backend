package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.EstadoOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPago {
    @Autowired
    private OrdenPagoRepository ordenRepo;

    @Transactional
    public void registrarPago(Long ordenId) {
        OrdenDePago orden = ordenRepo.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (orden.expirada()) {
            throw new RuntimeException("Orden expirada, no se puede pagar");
        }

        orden.setEstado(EstadoOrden.PAGADO);
        ordenRepo.save(orden);
    }
}
