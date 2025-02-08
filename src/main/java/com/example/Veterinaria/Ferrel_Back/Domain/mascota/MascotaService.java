package com.example.Veterinaria.Ferrel_Back.Domain.mascota;

import com.example.Veterinaria.Ferrel_Back.Domain.mascota.Mascota;
import com.example.Veterinaria.Ferrel_Back.Domain.mascota.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    public List<Mascota> listarMascotasPorDNI(Long dni) {
        return mascotaRepository.findByCliente_Dni(dni);
    }

    public Mascota obtenerMascotaPorId(Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    public Mascota agregarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota actualizarMascota(Long id, Mascota mascotaActualizada) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);
        if (mascota != null) {
            mascota = mascotaActualizada;  // Puedes mejorar esto copiando los campos uno por uno.
            return mascotaRepository.save(mascota);
        }
        return null;
    }

    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
}

