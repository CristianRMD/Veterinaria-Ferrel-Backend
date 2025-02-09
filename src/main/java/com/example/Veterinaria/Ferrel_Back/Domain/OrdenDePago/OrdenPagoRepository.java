package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenPagoRepository extends JpaRepository<OrdenDePago, Long>{

}
