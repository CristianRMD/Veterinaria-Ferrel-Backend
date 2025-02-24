package com.example.Veterinaria.Ferrel_Back.Domain.mascota;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByCliente_Dni(Long dni);
    List<Mascota> findByClienteActivoTrue();
}

