package com.example.Veterinaria.Ferrel_Back.Domain.historialMedico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Integer> {
    List<HistorialMedico> findByMascota_Id(Long idMascota);
}