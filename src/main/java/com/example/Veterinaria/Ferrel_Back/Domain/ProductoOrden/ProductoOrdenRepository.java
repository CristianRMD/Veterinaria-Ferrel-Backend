package com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoOrdenRepository extends JpaRepository<ProductoOrden, Long>{
    List<ProductoOrden> findByOrdenDePago_IdOrden(Long idOrden);
}
