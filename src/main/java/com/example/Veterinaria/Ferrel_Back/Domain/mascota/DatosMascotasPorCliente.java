package com.example.Veterinaria.Ferrel_Back.Domain.mascota;

import java.util.List;

public record DatosMascotasPorCliente(
        Long idCliente,
        String nombreCliente,
        List<DatosListadoMascota> mascotas
) {
}
