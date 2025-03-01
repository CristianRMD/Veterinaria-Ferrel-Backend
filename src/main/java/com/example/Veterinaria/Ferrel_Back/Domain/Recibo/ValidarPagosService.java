package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;

import com.example.Veterinaria.Ferrel_Back.Domain.cliente.Cliente;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ValidarPagosService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Long validarDocumento(String documento) {
        // Validar formato: 8 o 9 dígitos
        if (!documento.matches("\\d{8}|\\d{9}")) {
            return 0L;
        }

        Long dni;
        try {
            dni = Long.parseLong(documento);
        } catch (NumberFormatException e) {
            return 0L;
        }

        return clienteRepository.findByDni(dni)
                .map(Cliente::getId)
                .orElse(0L);
    }
}
