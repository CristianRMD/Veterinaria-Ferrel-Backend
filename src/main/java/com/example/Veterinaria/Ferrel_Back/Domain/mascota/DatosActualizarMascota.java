package com.example.Veterinaria.Ferrel_Back.Domain.mascota;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarMascota(
        String nombre,
        String raza,
        Integer edad,
        String sexo,
        Float peso,
        Float talla
) {
}
