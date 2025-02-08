package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenPagoRepository;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrdenRepository;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.Cliente;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.ClienteRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Recibo")
public class Recibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_recibo;
    int id_cliente;
    int id_orden;
    double monto_total;


    public Recibo(int id_cliente, int id_orden, double monto_total) {
        this.id_cliente = id_cliente;
        this.id_orden = id_orden;
        this.monto_total = monto_total;
    }

    @Service
    public static class RegistrarPagosService {
        @Autowired
        private OrdenPagoRepository ordenPagoRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private ProductoOrdenRepository productoOrdenRepository;

        @Autowired
        private ReciboRepository reciboRepository;

        public List<ProductoOrden> obtenerOrdenPago() {
            return productoOrdenRepository.findAll();
        }

        public boolean registrarRecibo(int id_cliente, int id_orden, double monto_total) {
            try {
                Optional<OrdenDePago> ordenPago = ordenPagoRepository.findById((long) id_orden);
                if (ordenPago.isEmpty()) {
                    return false;
                }

                Optional<Cliente> cliente = clienteRepository.findById(id_cliente);
                if (cliente.isEmpty()) {
                    return false;
                }

                Recibo nuevoRecibo = new Recibo(id_cliente, id_orden, monto_total);
                reciboRepository.save(nuevoRecibo);

                return true;
            } catch (Exception e) {
                return false;
            }
        }



        public boolean validarDocumento(String documento) {
            if (documento.isEmpty() || documento.length() < 8 || documento.length() > 11) {
                return false;
            }

            try {
                Long.valueOf(documento);
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }

        public double calcularMontoTotalConIGV(List<Double> subtotales, double porcentajeIGV) {
            double montoTotal = subtotales.stream().mapToDouble(Double::doubleValue).sum();
            return montoTotal + (montoTotal * (porcentajeIGV / 100));
        }
    }
}
