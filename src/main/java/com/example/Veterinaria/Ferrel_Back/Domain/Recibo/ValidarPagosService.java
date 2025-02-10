package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;

import com.example.Veterinaria.Ferrel_Back.Domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ValidarPagosService {


    @Autowired
    private ClienteRepository clienteRepository;


    public boolean validarDocumento(String documento) {
        // Validar formato: 8 o 9 dígitos
        if (!documento.matches("\\d{8}|\\d{9}")) {
            return false;
        }

        Long dni;
        try {
            dni = Long.parseLong(documento);
        } catch (NumberFormatException e) {
            return false;
        }

        return clienteRepository.findByDni(dni).isPresent();
    }

    public double calcularMontoTotalConIGV(List<Double> subtotales, double porcentajeIGV) {
        double montoTotal = subtotales.stream().mapToDouble(Double::doubleValue).sum();
        return montoTotal + (montoTotal * (porcentajeIGV / 100));
    }
}
