package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoRepository;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrdenRepository;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegistrarPagosService {


    @Autowired
    private OrdenPagoRepository ordenPagoRepository;

    @Autowired
    private ProductoOrdenRepository productoOrdenRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ProductoOrden> obtenerOrdenPago() {
        return productoOrdenRepository.findAll();
    }

    public boolean registrarRecibo(int idCliente, Integer idOrdenDePago, Double montoTotal) { // Cambiar a Double
        try {
            if (montoTotal == null || montoTotal <= 0) { // Validación adicional
                return false;
            }

            OrdenDePago ordenPago = new OrdenDePago(); // falta pasarle en el constructor el monto total
            // Considera relacionar con idCliente e idOrdenDePago aquí
            ordenPagoRepository.save(ordenPago);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
