package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.historialMedico.HistorialMedicoService;
import com.example.Veterinaria.Ferrel_Back.Domain.mascota.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaMascota> registrarMascota(@RequestBody @Valid DatosRegistroMascota datosRegistroMascota) {
        DatosRespuestaMascota mascota = mascotaService.agregarMascota(datosRegistroMascota);
        return ResponseEntity.ok(mascota); // Solo devuelve el objeto sin la URL de ubicación
    }

    @GetMapping("/list")
    public ResponseEntity<List<DatosListadoMascota>> listaMascotas() {
        return ResponseEntity.ok(mascotaService.listarMascotas());
    }

    @GetMapping("/cliente/{dni}")
    public ResponseEntity<DatosMascotasPorCliente> listarMascotasPorCliente(@PathVariable Long dni) {
        return ResponseEntity.ok(mascotaService.listarMascotasPorDNI(dni));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMascota> retornarDatosMascota(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.obtenerMascotaPorId(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DatosRespuestaMascota> actualizarMascota(@PathVariable Long id,
                                                                   @RequestBody @Valid DatosActualizarMascota datosActualizarMascota) {
        return ResponseEntity.ok(mascotaService.actualizarMascota(id, datosActualizarMascota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }
}
