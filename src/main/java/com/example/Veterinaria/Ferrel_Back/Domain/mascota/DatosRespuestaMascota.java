package com.example.Veterinaria.Ferrel_Back.Domain.mascota;

import com.example.Veterinaria.Ferrel_Back.Domain.cliente.DatosRespuestaCliente;

public record DatosRespuestaMascota(
        Long id,
        String nombre,
        String raza,
        int edad,
        String sexo,
        float peso,
        float talla,
        DatosRespuestaCliente cliente
) {
    public DatosRespuestaMascota(Mascota mascota) {
        this(mascota.getId(), mascota.getNombre(), mascota.getRaza(), mascota.getEdad(),
                mascota.getSexo(), mascota.getPeso(), mascota.getTalla(),
                new DatosRespuestaCliente(mascota.getCliente()));
    }
}
