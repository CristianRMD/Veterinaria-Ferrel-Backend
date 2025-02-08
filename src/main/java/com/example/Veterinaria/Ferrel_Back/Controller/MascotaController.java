package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.mascota.*;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.Cliente;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaMascota> registrarMascota(@RequestBody @Valid DatosRegistroMascota datosRegistroMascota,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        Cliente cliente = clienteRepository.getReferenceById(datosRegistroMascota.clienteId());
        Mascota mascota = new Mascota(
                null, datosRegistroMascota.nombre(), datosRegistroMascota.raza(), datosRegistroMascota.edad(),
                datosRegistroMascota.sexo(), datosRegistroMascota.peso(), datosRegistroMascota.talla(), cliente);

        mascota = mascotaRepository.save(mascota);
        URI url = uriComponentsBuilder.path("/mascota/{id}").buildAndExpand(mascota.getId()).toUri();

        return ResponseEntity.created(url).body(new DatosRespuestaMascota(mascota));
    }

    @GetMapping("/details")
    public ResponseEntity<Page<DatosListadoMascota>> listaMascotas(Pageable paginacion) {
        return ResponseEntity.ok(mascotaRepository.findAll(paginacion).map(DatosListadoMascota::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMascota> retornarDatosMascota(@PathVariable Long id) {
        Mascota mascota = mascotaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaMascota(mascota));
    }

    @PutMapping("/edit")
    @Transactional
    public ResponseEntity<DatosRespuestaMascota> actualizarMascota(@RequestBody @Valid DatosActualizarMascota datosActualizarMascota) {
        Mascota mascota = mascotaRepository.getReferenceById(datosActualizarMascota.id());

        if (datosActualizarMascota.nombre() != null) mascota.setNombre(datosActualizarMascota.nombre());
        if (datosActualizarMascota.raza() != null) mascota.setRaza(datosActualizarMascota.raza());
        if (datosActualizarMascota.edad() != null) mascota.setEdad(datosActualizarMascota.edad());
        if (datosActualizarMascota.sexo() != null) mascota.setSexo(datosActualizarMascota.sexo());
        if (datosActualizarMascota.peso() != null) mascota.setPeso(datosActualizarMascota.peso());
        if (datosActualizarMascota.talla() != null) mascota.setTalla(datosActualizarMascota.talla());

        return ResponseEntity.ok(new DatosRespuestaMascota(mascota));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
