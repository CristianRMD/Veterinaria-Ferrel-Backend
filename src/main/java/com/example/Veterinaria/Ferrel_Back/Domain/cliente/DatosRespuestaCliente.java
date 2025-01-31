package com.example.Veterinaria.Ferrel_Back.Domain.cliente;

public record DatosRespuestaCliente(
        Long id ,
        String nombre ,
        String apellido,
        Long dni

) {
    public DatosRespuestaCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNombre(), cliente.getApellido(),cliente.getDni());

    }
}
