package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.historialMedico.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historiales")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (para frontend)
public class HistorialMedicoController {

    private final HistorialMedicoService historialMedicoService;

    public HistorialMedicoController(HistorialMedicoService historialMedicoService) {
        this.historialMedicoService = historialMedicoService;
    }

    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<DatosHistorialesPorMascota> obtenerHistorialesPorMascota(@PathVariable Long idMascota) {
        var respuesta = historialMedicoService.obtenerHistorialesPorMascota(idMascota);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoHistorial> obtenerHistorialPorId(@PathVariable Integer id) {
        DatosListadoHistorial historial = historialMedicoService.obtenerHistorialPorId(id);
        return ResponseEntity.ok(historial);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> registrarHistorial(@PathVariable Long id, @RequestBody @Valid DatosRegistrarHistorial datos) {
        historialMedicoService.registrarHistorial(id, datos);
        return ResponseEntity.ok("Historial médico registrado con éxito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarHistorial(@PathVariable int id, @RequestBody @Valid DatosActualizarHistorial datos) {
        historialMedicoService.actualizarHistorial(id, datos);
        return ResponseEntity.ok("Historial médico actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHistorial(@PathVariable int id) {
        historialMedicoService.eliminarHistorial(id);
        return ResponseEntity.ok("Historial médico eliminado correctamente");
    }
}