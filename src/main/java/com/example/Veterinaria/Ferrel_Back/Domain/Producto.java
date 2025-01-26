package com.example.Veterinaria.Ferrel_Back.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private int id_producto;
    private String nombre;
    private float precio_unitario;
    private int stock;


}
