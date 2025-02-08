package com.example.Veterinaria.Ferrel_Back.Domain.mascota;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroMascota(
        @NotNull String nombre,
        @NotNull String raza,
        @NotNull int edad,
        @NotNull String sexo,
        @NotNull float peso,
        @NotNull float talla,
        @NotNull Long clienteId
) {
}
