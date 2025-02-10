package com.example.Veterinaria.Ferrel_Back.Domain.historialMedico;

import java.util.Date;

public record DatosListadoHistorial(
        Integer idhistorial,
        Long idMascota,
        String tipoconsulta,
        String motivo,
        Float temperatura,
        String resumen,
        String receta,
        Date fecha
) {
    public DatosListadoHistorial(HistorialMedico historial) {
        this(
                historial.getIdhistorial(),
                historial.getMascota().getId(),
                historial.getTipoconsulta(),
                historial.getMotivo(),
                historial.getTemperatura(),
                historial.getResumen(),
                historial.getReceta(),
                historial.getFecha()
        );
    }
}
