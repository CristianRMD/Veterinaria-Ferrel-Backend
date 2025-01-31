package com.example.Veterinaria.Ferrel_Back.Domain.cliente;

public record DatosListadoCliente (
        Long id , String nombre,String apellido ,Long dni, String email,String telefono
) {
public DatosListadoCliente (Cliente cliente ){
    this (cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getDni(),cliente.getEmail(), cliente.getTelefono());
}
}
