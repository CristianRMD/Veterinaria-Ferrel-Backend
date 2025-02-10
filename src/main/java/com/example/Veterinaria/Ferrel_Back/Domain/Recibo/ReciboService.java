package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.ReciboOrden.ReciboOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.ReciboOrden.ReciboOrdenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class ReciboService {

    private final ReciboRepository reciboRepository;
    private final ReciboOrdenRepository reciboOrdenRepository;
    private final CarritoReciboService carritoReciboService;

    public ReciboService(ReciboRepository reciboRepository, ReciboOrdenRepository reciboOrdenRepository, CarritoReciboService carritoReciboService) {
        this.reciboRepository = reciboRepository;
        this.reciboOrdenRepository = reciboOrdenRepository;
        this.carritoReciboService = carritoReciboService;
    }


    @Transactional
    public Recibo crearRecibo(List<OrdenDePago> ordenes, Long idCliente) {
        Recibo recibo = new Recibo();
        recibo.setFechaHoraRegistro(LocalDateTime.now(ZoneId.of("America/Lima")));



        BigDecimal totalOrdenes = ordenes.stream()
                .map(orden -> BigDecimal.valueOf(orden.getTotal()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalConsultas = carritoReciboService.obtenerConsultas().stream()
                .map(consulta -> BigDecimal.valueOf(consulta.getSubtotal()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal montoTotal = totalOrdenes.add(totalConsultas);
        BigDecimal igv = montoTotal.multiply(BigDecimal.valueOf(0.18));
        BigDecimal montoConIGV = montoTotal.add(igv).setScale(2, RoundingMode.HALF_UP);

        recibo.setMontoTotal(montoConIGV.doubleValue());

        recibo = reciboRepository.save(recibo);

        // vincula las ordenes al recibo
        for (OrdenDePago orden : ordenes) {
            ReciboOrden reciboOrden = new ReciboOrden();
            reciboOrden.setRecibo(recibo);
            reciboOrden.setOrdenDePago(orden);
            reciboOrdenRepository.save(reciboOrden);
        }

        return recibo;
    }



    @Transactional
    public void vincularOrdenARecibo(Long idRecibo, Long idOrden) {
        reciboOrdenRepository.insertarReciboOrden(idRecibo, idOrden);
    }
}
