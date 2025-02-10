package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;


public record CarritoReciboDTO(
        String tipo,
        String descripcion,
        int cantidad,
        double precioTotal

) {

    public double getSubtotal() {
        return precioTotal;
    }
}
