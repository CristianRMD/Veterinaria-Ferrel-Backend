package com.example.Veterinaria.Ferrel_Back.Domain.historialMedico;

import java.util.List;

public record DatosHistorialesPorMascota(
        Long idMascota,
        String nombre,
        List<DatosListadoHistorial> historiales
) {
}