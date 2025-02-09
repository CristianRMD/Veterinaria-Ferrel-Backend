package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.NroAtencion.NroAtencion;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.Cliente;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.ClienteRepository;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.DatosListadoCliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/nroatencion")
public class NroAtencionController {

    private final NroAtencion nroAtencionService;
    private ClienteRepository clienteRepository = null;

    public NroAtencionController(NroAtencion nroAtencionService, ClienteRepository clienteRepository) {
        this.nroAtencionService = nroAtencionService;
        this.clienteRepository = clienteRepository;
    }

    // endpoint para obtener el nroAtencion actual (/nroatencion)
    @GetMapping
    public ResponseEntity<Integer> obtenerNroAtencion() {
        return ResponseEntity.ok(nroAtencionService.obtenerNroAtencion());
    }

    // endpoint para incrementar el nroAtencion al finalizar cada impresion
    @PostMapping("/incrementar")
    public ResponseEntity<String> incrementarNroAtencion() {
        nroAtencionService.incrementarNroAtencion();
        return ResponseEntity.ok("Nro de atención incrementado a: " + nroAtencionService.obtenerNroAtencion());
    }


    // endpoint para validar el cliente por documento (validar-cliente?documento=12345678)
    @PostMapping("/validar-cliente")
    public ResponseEntity<?> validarCliente(@RequestParam String documento) {
        if (!documento.matches("\\d{8}|\\d{9}")) {
            return ResponseEntity.badRequest()
                    .body("Documento inválido. Debe ser un DNI (8 dígitos) o CE (9 dígitos).");
        }

        Long dni;
        try {
            dni = Long.parseLong(documento);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Documento invalido. Ingrese solo numeros...");
        }

        // buscar el cliente en la base de datos
        Optional<Cliente> optionalCliente = clienteRepository.findByDni(dni);
        if (optionalCliente.isEmpty()) {
            return ResponseEntity.badRequest().body("Cliente no encontrado. Debe registrarse...");
        }


        Cliente cliente = optionalCliente.get();
        DatosListadoCliente datosCliente = new DatosListadoCliente(cliente);


        return ResponseEntity.ok(datosCliente);
    }









}
