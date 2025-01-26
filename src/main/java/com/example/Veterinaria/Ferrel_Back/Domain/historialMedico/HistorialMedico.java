package com.example.Veterinaria.Ferrel_Back.Domain.historialMedico;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialMedico {

    private int id;

    private String tipoConsulta;// puede ser un enum
    private String motivo;
    private boolean esterilizado;
    private float temperatura;
    private String resumen;
    private String receta;
    //private Date fecha;
    private int idMascota;
}
