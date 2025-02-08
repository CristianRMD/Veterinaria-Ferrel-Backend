package com.example.Veterinaria.Ferrel_Back.Domain.mascota;

public record DatosListadoMascota(
        Long id,
        String nombre,
        String raza,
        int edad,
        String sexo
) {
    public DatosListadoMascota(Mascota mascota) {
        this(mascota.getId(), mascota.getNombre(), mascota.getRaza(), mascota.getEdad(), mascota.getSexo());
    }
}
