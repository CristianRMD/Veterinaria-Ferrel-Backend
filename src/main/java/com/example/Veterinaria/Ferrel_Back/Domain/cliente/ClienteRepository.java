package com.example.Veterinaria.Ferrel_Back.Domain.cliente;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface ClienteRepository extends JpaRepository<Cliente,Long>{

    List<Cliente> findByActivoTrue();

    // busqueda de clientes por dni
    Optional<Cliente> findByDni(Long dni);

    // busqueda de cliente por id

    Optional<Cliente> findById(int idCliente);

    boolean existsByDni(@NotNull Long dni);
}
