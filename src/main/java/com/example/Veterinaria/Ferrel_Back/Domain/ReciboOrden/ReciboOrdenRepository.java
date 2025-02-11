package com.example.Veterinaria.Ferrel_Back.Domain.ReciboOrden;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReciboOrdenRepository extends JpaRepository<ReciboOrden, Long> {


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO recibo_orden (id_recibo, id_orden) VALUES (:idRecibo, :idOrden)", nativeQuery = true)
    void insertarReciboOrden(@Param("idRecibo") Long idRecibo, @Param("idOrden") Long idOrden);

}
