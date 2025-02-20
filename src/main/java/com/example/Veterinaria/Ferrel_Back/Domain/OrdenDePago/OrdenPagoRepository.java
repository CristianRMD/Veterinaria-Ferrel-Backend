package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenPagoRepository extends JpaRepository<OrdenDePago, Long>{


    @EntityGraph(attributePaths = {"productos"})
    Optional<OrdenDePago> findById(Long idOrden);

    List<OrdenDePago> findByEstadoAndFechaExpiracionBefore(EstadoOrden estado, LocalDateTime fechaExpiracion);
}
