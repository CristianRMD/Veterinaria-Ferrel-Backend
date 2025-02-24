package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.historialMedico.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/historial")
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

    @GetMapping("/search/{id}")
    public ResponseEntity<DatosListadoHistorial> obtenerHistorialPorId(@PathVariable Integer id) {
        DatosListadoHistorial historial = historialMedicoService.obtenerHistorialPorId(id);
        return ResponseEntity.ok(historial);
    }

    @PostMapping("/register/{id}")
    public ResponseEntity<DatosListadoHistorial> registrarHistorial(@PathVariable Long id, @RequestBody @Valid DatosRegistrarHistorial datos) {
        DatosListadoHistorial historia = historialMedicoService.registrarHistorial(id, datos);
        return ResponseEntity.ok(historia);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DatosListadoHistorial> actualizarHistorial(@PathVariable int id, @RequestBody @Valid DatosActualizarHistorial datos) {
        return ResponseEntity.ok(historialMedicoService.actualizarHistorial(id, datos));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable int id) {
        historialMedicoService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }
}