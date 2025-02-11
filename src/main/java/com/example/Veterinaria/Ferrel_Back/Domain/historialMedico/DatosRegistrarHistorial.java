package com.example.Veterinaria.Ferrel_Back.Domain.historialMedico;

import jakarta.validation.constraints.*;
import java.util.Date;

public record DatosRegistrarHistorial(

        @NotNull(message = "El tipo de consulta no puede estar vacío")
        String tipoconsulta,

        @NotNull(message = "El motivo no puede estar vacío")
        String motivo,

        @NotNull(message = "Temperatura no puede estar vacío")
        Float temperatura,

        @NotNull(message = "El resumen no puede estar vacío")
        String resumen,

        @NotNull(message = "La receta no puede estar vacía")
        String receta,

        @PastOrPresent(message = "La fecha no puede ser futura")
        Date fecha
) {}