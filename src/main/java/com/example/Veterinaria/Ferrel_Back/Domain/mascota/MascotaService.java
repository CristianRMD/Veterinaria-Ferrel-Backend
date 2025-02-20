package com.example.Veterinaria.Ferrel_Back.Domain.mascota;

import com.example.Veterinaria.Ferrel_Back.Domain.cliente.Cliente;
import com.example.Veterinaria.Ferrel_Back.Domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<DatosListadoMascota> listarMascotas() {
        return mascotaRepository.findByClienteActivoTrue()
                .stream()
                .map(DatosListadoMascota::new)
                .collect(Collectors.toList());
    }

    public DatosMascotasPorCliente listarMascotasPorDNI(Long dni) {
        Cliente cliente = clienteRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con DNI: " + dni));

        List<DatosListadoMascota> mascotas = mascotaRepository.findByCliente_Dni(dni)
                .stream()
                .map(DatosListadoMascota::new)
                .collect(Collectors.toList());

        return new DatosMascotasPorCliente(cliente.getId(), cliente.getNombre(), mascotas);
    }

    public DatosRespuestaMascota obtenerMascotaPorId(Long id) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));

        return new DatosRespuestaMascota(mascota);
    }

    public DatosRespuestaMascota agregarMascota(DatosRegistroMascota datosRegistroMascota) {
        Cliente cliente = clienteRepository.findById(datosRegistroMascota.clienteId())
               .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + datosRegistroMascota.clienteId()));

        Mascota mascota = new Mascota(
                null, datosRegistroMascota.nombre(), datosRegistroMascota.raza(), datosRegistroMascota.edad(),
                datosRegistroMascota.sexo(), datosRegistroMascota.peso(), datosRegistroMascota.talla(), cliente
        );

        mascota = mascotaRepository.save(mascota);
        return new DatosRespuestaMascota(mascota);
    }

    public DatosRespuestaMascota actualizarMascota(Long id, DatosActualizarMascota datos) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));

        if (datos.nombre() != null) mascota.setNombre(datos.nombre());
        if (datos.raza() != null) mascota.setRaza(datos.raza());
        if (datos.edad() != null) mascota.setEdad(datos.edad());
        if (datos.sexo() != null) mascota.setSexo(datos.sexo());
        if (datos.peso() != null) mascota.setPeso(datos.peso());
        if (datos.talla() != null) mascota.setTalla(datos.talla());

        mascota = mascotaRepository.save(mascota);
        return new DatosRespuestaMascota(mascota);
    }

    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
}
