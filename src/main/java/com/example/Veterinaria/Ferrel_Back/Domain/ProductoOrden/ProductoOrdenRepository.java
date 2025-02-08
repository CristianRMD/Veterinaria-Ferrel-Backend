package com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoOrdenRepository extends JpaRepository<ProductoOrden, Long>{
}
