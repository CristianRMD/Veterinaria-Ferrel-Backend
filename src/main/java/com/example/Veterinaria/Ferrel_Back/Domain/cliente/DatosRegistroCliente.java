package com.example.Veterinaria.Ferrel_Back.Domain.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCliente(
        @NotNull
         String nombre ,
        @NotNull
        String apellido ,
        @NotNull
        String telefono ,
        @NotNull
        String direccion ,
        @NotNull
        @Email
        String email
) {
}
