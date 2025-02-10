package com.example.Veterinaria.Ferrel_Back.Domain.historialMedico;

import jakarta.validation.constraints.*;
import java.util.Date;

public record DatosActualizarHistorial(

        @NotBlank(message = "El tipo de consulta no puede estar vacío")
        String tipoconsulta,

        @NotBlank(message = "El motivo no puede estar vacío")
        String motivo,

        Float temperatura,

        @NotBlank(message = "El resumen no puede estar vacío")
        String resumen,

        @NotBlank(message = "La receta no puede estar vacía")
        String receta,

        @PastOrPresent(message = "La fecha no puede ser futura")
        Date fecha
) {}