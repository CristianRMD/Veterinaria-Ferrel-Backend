package com.example.Veterinaria.Ferrel_Back.Domain.cliente;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCliente(
        @NotNull
         Long id ,
         String nombre ,
         String apellido ,
         Long dni,
         String telefono ,
         String direccion ,
         String email

) {
}
